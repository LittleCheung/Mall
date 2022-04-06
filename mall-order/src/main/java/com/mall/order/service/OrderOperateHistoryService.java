package com.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.order.entity.OrderOperateHistoryEntity;
import com.yxj.gulimall.common.utils.PageUtils;

import java.util.Map;
/**
 * @author yaoxinjia
 * @email 894548575@qq.com
 */
public interface OrderOperateHistoryService extends IService<OrderOperateHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

