package com.yxj.gulimall.order.vo;

import lombok.Data;
/**
 * @author yaoxinjia
 * @email 894548575@qq.com
 */
@Data
public class PayVo {

    private String out_trade_no; // 商户订单号 必填
    private String subject; // 订单名称 必填
    private String total_amount;  // 付款金额 必填
    private String body; // 商品描述 可空
}
