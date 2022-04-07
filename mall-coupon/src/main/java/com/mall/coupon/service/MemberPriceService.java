package com.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.coupon.entity.MemberPriceEntity;
import com.mall.common.utils.PageUtils;

import java.util.Map;

/**
 * 商品会员价格
 * @author littlecheung
 */
public interface MemberPriceService extends IService<MemberPriceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

