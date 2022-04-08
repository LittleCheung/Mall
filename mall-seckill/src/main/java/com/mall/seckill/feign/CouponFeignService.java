package com.mall.seckill.feign;

import com.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 优惠券服务远程调用代码
 * @author yaoxinjia
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    /**
     * 查询最近三天需要参加秒杀商品的信息
     * @return
     */
    @GetMapping(value = "/coupon/seckillsession/Lates3DaySession")
    R getLates3DaySession();

}
