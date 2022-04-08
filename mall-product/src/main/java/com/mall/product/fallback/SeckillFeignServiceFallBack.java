package com.mall.product.fallback;

import com.mall.common.exception.BizCodeEnum;
import com.mall.common.utils.R;
import com.mall.product.feign.SeckillFeignService;
import org.springframework.stereotype.Component;

/**
 * 远程调用秒杀模块失败处理
 * @author littlecheung
 */
@Component
public class SeckillFeignServiceFallBack implements SeckillFeignService {
    @Override
    public R getSkuSeckilInfo(Long skuId) {
        return R.error(BizCodeEnum.TO_MANY_REQUEST.getCode(),BizCodeEnum.TO_MANY_REQUEST.getMsg());
    }
}
