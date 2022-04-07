package com.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.coupon.entity.HomeSubjectSpuEntity;
import com.mall.common.utils.PageUtils;

import java.util.Map;

/**
 * 专题商品
 * @author littlecheung
 */
public interface HomeSubjectSpuService extends IService<HomeSubjectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

