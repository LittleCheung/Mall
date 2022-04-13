package com.mall.order.feign;

import com.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 商品模块远程调用接口
 * @author littlecheung
 */
@FeignClient("mall-product")
public interface ProductFeignService {

    /**
     * 根据skuId查询spu的信息
     * @param skuId
     * @return
     */
    @GetMapping(value = "/product/spuinfo/skuId/{id}")
    public R getSpuInfoBySkuId(@PathVariable("skuId") Long skuId);

}
