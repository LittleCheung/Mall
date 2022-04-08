package com.mall.order.dao;

import com.mall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * @author littlecheung
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
