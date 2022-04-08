package com.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.order.entity.OrderItemEntity;
import com.mall.common.utils.PageUtils;

import java.util.Map;

/**
 *
 * @author littlecheung
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

