package com.mall.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mall.cart.exception.CartExceptionHandler;
import com.mall.cart.feign.ProductFeignService;
import com.mall.cart.interceptor.CartInterceptor;
import com.mall.cart.service.CartService;
import com.mall.cart.to.UserInfoTo;
import com.mall.cart.vo.CartItemVo;
import com.mall.cart.vo.CartVo;
import com.mall.cart.vo.SkuInfoVo;
import com.mall.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import static com.mall.common.constant.CartConstant.CART_PREFIX;



/**
 *
 * @author littlecheung
 */
@Slf4j
@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProductFeignService productFeignService;

    @Autowired
    private ThreadPoolExecutor executor;

    /**
     * 添加商品到购物车
     * @param skuId
     * @param num
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public CartItemVo addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException {

        //获取到要操作的购物车信息
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        String productRedisValue = (String) cartOps.get(skuId.toString());
        //判断购物车是否有该商品的信息，即redis中是否有存储信息
        if (StringUtils.isEmpty(productRedisValue)) {
            //没有就添加新商品到购物车
            CartItemVo cartItemVo = new CartItemVo();

            //获取sku信息的异步任务
            CompletableFuture<Void> getSkuInfoFuture = CompletableFuture.runAsync(() -> {
                //远程调用查询当前要添加商品的基本信息
                R productSkuInfo = productFeignService.getInfo(skuId);
                SkuInfoVo skuInfo = productSkuInfo.getData("skuInfo", new TypeReference<SkuInfoVo>() {});
                cartItemVo.setSkuId(skuInfo.getSkuId());
                cartItemVo.setTitle(skuInfo.getSkuTitle());
                cartItemVo.setImage(skuInfo.getSkuDefaultImg());
                cartItemVo.setPrice(skuInfo.getPrice());
                cartItemVo.setCount(num);
            }, executor);
            //获取sku属性值的异步任务
            CompletableFuture<Void> getSkuAttrValuesFuture = CompletableFuture.runAsync(() -> {
                //远程调用查询商品套餐属性信息
                List<String> skuSaleAttrValues = productFeignService.getSkuSaleAttrValues(skuId);
                cartItemVo.setSkuAttrValues(skuSaleAttrValues);
            }, executor);
            //等待所有的异步任务全部完成
            CompletableFuture.allOf(getSkuInfoFuture, getSkuAttrValuesFuture).get();

            //添加数据到Redis中
            String cartItemJson = JSON.toJSONString(cartItemVo);
            cartOps.put(skuId.toString(), cartItemJson);
            return cartItemVo;
        } else {
            //购物车有此商品，只需要修改数量
            CartItemVo cartItemVo = JSON.parseObject(productRedisValue, CartItemVo.class);
            cartItemVo.setCount(cartItemVo.getCount() + num);
            //更新Redis中存储的数据
            String cartItemJson = JSON.toJSONString(cartItemVo);
            cartOps.put(skuId.toString(),cartItemJson);
            return cartItemVo;
        }
    }


    /**
     * 获取购物车中某个购物项
     * @param skuId
     * @return
     */
    @Override
    public CartItemVo getCartItem(Long skuId) {

        //获取当前购物车
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        String redisValue = (String) cartOps.get(skuId.toString());
        //获取到购物车中具体购物项并返回
        CartItemVo cartItem = JSON.parseObject(redisValue, CartItemVo.class);
        return cartItem;
    }


    /**
     * 获取购物车中所有的数据
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public CartVo getCart() throws ExecutionException, InterruptedException {

        CartVo cartVo = new CartVo();
        //判断获取用户的登录状态
        UserInfoTo userInfoTo = CartInterceptor.toThreadLocal.get();
        //用户已登录
        if (userInfoTo.getUserId() != null) {
            //登录后用户购物车的键
            String cartKey = CART_PREFIX + userInfoTo.getUserId();
            //临时用户购物车的键
            String temptCartKey = CART_PREFIX + userInfoTo.getUserKey();
            //获取临时购物车里中的所有购物项
            List<CartItemVo> tempCartItems = getCartItems(temptCartKey);
            //如果临时购物车的数据不为空，即还未进行与登录后用户购物车进行信息合并
            if (tempCartItems != null) {
                //对临时购物车中的数据进行合并操作
                for (CartItemVo item : tempCartItems) {
                    addToCart(item.getSkuId(),item.getCount());
                }
                //清除临时购物车中的数据
                clearCartInfo(temptCartKey);
            }
            //3、获取登录后的购物车数据【包含合并过来的临时购物车的数据和登录后购物车的数据】
            List<CartItemVo> cartItems = getCartItems(cartKey);
            cartVo.setItems(cartItems);
        } else {
            //用户未登录
            String cartKey = CART_PREFIX + userInfoTo.getUserKey();
            //获取临时购物车中的所有购物项
            List<CartItemVo> cartItems = getCartItems(cartKey);
            cartVo.setItems(cartItems);
        }
        return cartVo;
    }


    /**
     * 获取到要操作的购物车
     * @return
     */
    private BoundHashOperations<String, Object, Object> getCartOps() {

        //获取当前用户信息
        UserInfoTo userInfoTo = CartInterceptor.toThreadLocal.get();
        String cartKey = "";
        //用户已登录
        if (userInfoTo.getUserId() != null) {
            //将获取到具体用户的购物车
            cartKey = CART_PREFIX + userInfoTo.getUserId();
        } else {
            //将获取到临时购物车
            cartKey = CART_PREFIX + userInfoTo.getUserKey();
        }
        //操作Redis进行获取
        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(cartKey);
        return operations;
    }


    /**
     * 获取购物车里中的所有购物项
     * @param cartKey
     * @return
     */
    private List<CartItemVo> getCartItems(String cartKey) {

        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(cartKey);
        List<Object> values = operations.values();
        if (values != null && values.size() > 0) {
            List<CartItemVo> cartItems = values.stream().map((obj) -> {
                String str = (String) obj;
                CartItemVo cartItem = JSON.parseObject(str, CartItemVo.class);
                return cartItem;
            }).collect(Collectors.toList());
            return cartItems;
        }
        return null;
    }

    /**
     * 清除购物车信息
     * @param cartKey
     */
    @Override
    public void clearCartInfo(String cartKey) {
        redisTemplate.delete(cartKey);
    }


    /**
     * 选中购物车中的购物项
     * @param skuId
     * @param check
     */
    @Override
    public void checkItem(Long skuId, Integer check) {

        //获取购物车中的购物项
        CartItemVo cartItem = getCartItem(skuId);
        //修改商品状态
        cartItem.setCheck(check == 1 ? true : false);
        //序列化存入redis中，key为所选中的购物项id，value为购物项修改后的新值
        String redisValue = JSON.toJSONString(cartItem);
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        cartOps.put(skuId.toString(),redisValue);
    }


    /**
     * 修改购物项的数量
     * @param skuId
     * @param num
     */
    @Override
    public void changeItemCount(Long skuId, Integer num) {

        //查询购物车中的购物项
        CartItemVo cartItem = getCartItem(skuId);
        //修改购物项数量
        cartItem.setCount(num);
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        String redisValue = JSON.toJSONString(cartItem);
        cartOps.put(skuId.toString(),redisValue);
    }


    /**
     * 根据skuid删除购物项
     * @param skuId
     */
    @Override
    public void deleteIdCartInfo(Integer skuId) {

        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        cartOps.delete(skuId.toString());
    }


    /**
     * 获取当前用户购物车中的购物项
     * @return
     */
    @Override
    public List<CartItemVo> getUserCartItems() {

        List<CartItemVo> cartItemVoList = new ArrayList<>();
        //获取当前登录用户信息
        UserInfoTo userInfoTo = CartInterceptor.toThreadLocal.get();
        //如果用户未登录直接返回null
        if (userInfoTo.getUserId() == null) {
            return null;
        } else {
            String cartKey = CART_PREFIX + userInfoTo.getUserId();
            //获取所有的购物项
            List<CartItemVo> cartItems = getCartItems(cartKey);
            if (cartItems == null) {
                throw new CartExceptionHandler();
            }
            //筛选出选中的购物项
            cartItemVoList = cartItems.stream()
                    .filter(items -> items.getCheck())
                    .map(item -> {
                        //更新为最新的价格
                        BigDecimal price = productFeignService.getPrice(item.getSkuId());
                        item.setPrice(price);
                        return item;
                    })
                    .collect(Collectors.toList());
        }
        return cartItemVoList;
    }
}
