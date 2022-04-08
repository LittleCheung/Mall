package com.mall.seckill.feign;

import com.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品服务远程调用代码
 * @author littlecheung
 */
@FeignClient("mall-product")
public interface ProductFeignService {

    @RequestMapping("/product/skuinfo/info/{skuId}")
    R getSkuInfo(@PathVariable("skuId") Long skuId);

}
