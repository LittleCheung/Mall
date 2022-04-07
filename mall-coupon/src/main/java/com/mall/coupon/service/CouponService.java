package com.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.coupon.entity.CouponEntity;
import com.mall.common.utils.PageUtils;

import java.util.Map;

/**
 * 优惠券信息
 * @author littlecheung
 */
public interface CouponService extends IService<CouponEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

