package com.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.coupon.entity.HomeSubjectSpuEntity;
import com.yxj.gulimall.common.utils.PageUtils;

import java.util.Map;

/**
 * 专题商品
 *
 * @author yaoxinjia
 * @email 894548575@qq.com
 * @date 2021-02-09 20:18:04
 */
public interface HomeSubjectSpuService extends IService<HomeSubjectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

