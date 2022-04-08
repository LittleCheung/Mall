package com.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.order.entity.OrderReturnReasonEntity;
import com.mall.common.utils.PageUtils;

import java.util.Map;

/**
 *
 * @author littlecheung
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

