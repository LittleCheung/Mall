package com.mall.order.to;

import com.mall.order.entity.OrderEntity;
import com.mall.order.entity.OrderItemEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单创建好后的实体
 * @author littlecheung
 */
@Data
public class OrderCreateTo {

    /** 订单 **/
    private OrderEntity order;

    /** 订单项 **/
    private List<OrderItemEntity> orderItems;

    /** 订单计算的应付价格 **/
    private BigDecimal payPrice;

    /** 运费 **/
    private BigDecimal fare;

}
