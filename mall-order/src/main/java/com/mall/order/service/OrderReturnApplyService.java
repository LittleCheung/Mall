package com.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.order.entity.OrderReturnApplyEntity;
import com.yxj.gulimall.common.utils.PageUtils;

import java.util.Map;

/**
 * @author yaoxinjia
 * @email 894548575@qq.com
 */
public interface OrderReturnApplyService extends IService<OrderReturnApplyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

