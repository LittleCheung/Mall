package com.mall.order.vo;

import lombok.Data;

import java.math.BigDecimal;


/**
 * 订单提交实体
 * @author littlecheung
 */
@Data
public class OrderSubmitVo {

    /** 收货地址的id **/
    private Long addrId;

    /** 支付方式 **/
    private Integer payType;

    /** 防重令牌 **/
    private String orderToken;

    /** 应付价格 **/
    private BigDecimal payPrice;

    /** 订单备注 **/
    private String remarks;
}
