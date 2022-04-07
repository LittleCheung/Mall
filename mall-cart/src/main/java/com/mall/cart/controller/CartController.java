package com.mall.cart.controller;

import com.mall.cart.service.CartService;
import com.mall.cart.vo.CartItemVo;
import com.mall.cart.vo.CartVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * 处理购物车服务请求
 * @author littlecheung
 */
@Controller
public class CartController {

    @Resource
    private CartService cartService;

    /**
     * 获取当前用户的购物车商品项
     * @return
     */
    @GetMapping(value = "/currentUserCartItems")
    @ResponseBody
    public List<CartItemVo> getCurrentCartItems() {

        List<CartItemVo> cartItemVoList = cartService.getUserCartItems();

        return cartItemVoList;
    }

    /**
     * 跳转到购物车详情页
     * @param model
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping(value = "/cart.html")
    public String cartListPage(Model model) throws ExecutionException, InterruptedException {

        CartVo cartVo = cartService.getCart();
        model.addAttribute("cart",cartVo);
        return "cartList";
    }

    /**
     * 添加商品到购物车
     * @param skuId
     * @param num
     * @param attributes
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping(value = "/addCartItem")
    public String addCartItem(@RequestParam("skuId") Long skuId,
                              @RequestParam("num") Integer num,
                              RedirectAttributes attributes) throws ExecutionException, InterruptedException {

        cartService.addToCart(skuId,num);

        // attributes.addAttribute():将数据放在url后面
        // 另外还有attributes.addFlashAttribute():将数据放在session中，可以在页面中取出，但是只能取一次
        attributes.addAttribute("skuId",skuId);
        return "redirect:http://cart.mall.com/addToCartSuccessPage.html";
    }


    /**
     * 跳转到添加购物车成功页面
     * @param skuId
     * @param model
     * @return
     */
    @GetMapping(value = "/addToCartSuccessPage.html")
    public String addToCartSuccessPage(@RequestParam("skuId") Long skuId,
                                       Model model) {
        //重定向到成功页面。再次查询购物车数据即可
        CartItemVo cartItemVo = cartService.getCartItem(skuId);
        model.addAttribute("cartItem",cartItemVo);
        return "success";
    }


    /**
     * 购物车中商品是否选中
     * @param skuId
     * @param checked
     * @return
     */
    @GetMapping(value = "/checkItem")
    public String checkItem(@RequestParam(value = "skuId") Long skuId,
                            @RequestParam(value = "checked") Integer checked) {

        cartService.checkItem(skuId,checked);

        return "redirect:http://cart.mall.com/cart.html";

    }


    /**
     * 改变商品数量
     * @param skuId
     * @param num
     * @return
     */
    @GetMapping(value = "/countItem")
    public String countItem(@RequestParam(value = "skuId") Long skuId,
                            @RequestParam(value = "num") Integer num) {

        cartService.changeItemCount(skuId,num);

        return "redirect:http://cart.mall.com/cart.html";
    }


    /**
     * 删除购物车商品信息
     * @param skuId
     * @return
     */
    @GetMapping(value = "/deleteItem")
    public String deleteItem(@RequestParam("skuId") Integer skuId) {

        cartService.deleteIdCartInfo(skuId);

        return "redirect:http://cart.gulimall.com/cart.html";
    }

}
