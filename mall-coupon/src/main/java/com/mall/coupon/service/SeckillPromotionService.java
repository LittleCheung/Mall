package com.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.coupon.entity.SeckillPromotionEntity;
import com.mall.common.utils.PageUtils;

import java.util.Map;

/**
 * 秒杀活动
 * @author littlecheung
 */
public interface SeckillPromotionService extends IService<SeckillPromotionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

