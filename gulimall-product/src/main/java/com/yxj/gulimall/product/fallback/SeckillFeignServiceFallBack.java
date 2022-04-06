package com.yxj.gulimall.product.fallback;

import com.yxj.gulimall.common.exception.BizCodeEnum;
import com.yxj.gulimall.common.utils.R;
import com.yxj.gulimall.product.feign.SeckillFeignService;
import org.springframework.stereotype.Component;

/**
 * @author yaoxinjia
 */
@Component
public class SeckillFeignServiceFallBack implements SeckillFeignService {
    @Override
    public R getSkuSeckilInfo(Long skuId) {
        return R.error(BizCodeEnum.TO_MANY_REQUEST.getCode(),BizCodeEnum.TO_MANY_REQUEST.getMsg());
    }
}
