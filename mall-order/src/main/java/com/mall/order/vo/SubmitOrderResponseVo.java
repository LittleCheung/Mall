package com.mall.order.vo;

import com.mall.order.entity.OrderEntity;
import lombok.Data;

/**
 * @author yaoxinjia
 * @email 894548575@qq.com
 */
@Data
public class SubmitOrderResponseVo {

    private OrderEntity order;

    /** 错误状态码 **/
    private Integer code;


}
