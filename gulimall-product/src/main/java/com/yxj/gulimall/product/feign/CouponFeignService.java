package com.yxj.gulimall.product.feign;


import com.yxj.gulimall.common.to.SkuReductionTo;
import com.yxj.gulimall.common.to.SpuBoundTo;
import com.yxj.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 1 CouponFeignService.saveSpuBounds(spuBoundTo)
 *      1.1 @RequestBody将这个对象转为json
 *      1.2 找到gulimall-coupon服务，给/coupon/spubounds/save发送请求
 *          将上一步转的json放在请求体位置，发送请求
 *       1.3 对方服务收到请求。请求体里有json数据
 *       (@RequestBody SpuBoundsEntity spuBounds); 将请求体的json转为SpuBoundsEntity
 *       只要json数据模型是兼容的。双方服务无需使用同一个to
 *       @author yaoxinjia
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);

    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);
}
