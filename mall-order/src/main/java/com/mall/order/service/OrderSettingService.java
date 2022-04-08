package com.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.order.entity.OrderSettingEntity;
import com.mall.common.utils.PageUtils;

import java.util.Map;

/**
 *
 * @author littlecheung
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

