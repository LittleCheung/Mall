package com.mall.order.vo;

import com.mall.order.entity.OrderEntity;
import lombok.Data;

/**
 * 提交订单后响应实体
 * @author littlecheung
 */
@Data
public class SubmitOrderResponseVo {

    private OrderEntity order;

    /** 错误状态码 **/
    private Integer code;

}
