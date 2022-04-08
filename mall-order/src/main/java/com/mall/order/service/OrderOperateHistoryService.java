package com.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.order.entity.OrderOperateHistoryEntity;
import com.mall.common.utils.PageUtils;

import java.util.Map;

/**
 *
 * @author yaoxinjia
 */
public interface OrderOperateHistoryService extends IService<OrderOperateHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

