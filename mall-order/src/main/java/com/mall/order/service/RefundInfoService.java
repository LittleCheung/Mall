package com.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.order.entity.RefundInfoEntity;
import com.yxj.gulimall.common.utils.PageUtils;

import java.util.Map;

/**
 * @author yaoxinjia
 * @email 894548575@qq.com
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

