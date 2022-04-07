package com.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.coupon.entity.CouponSpuCategoryRelationEntity;
import com.mall.common.utils.PageUtils;

import java.util.Map;

/**
 * 优惠券分类关联
 * @author littlecheung
 */
public interface CouponSpuCategoryRelationService extends IService<CouponSpuCategoryRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

