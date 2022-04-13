package com.mall.order.vo;

import lombok.Data;

import java.util.List;

/**
 * 库存锁定实体
 * @author littlecheung
 */
@Data
public class WareSkuLockVo {

    /**
     * 订单号
     */
    private String orderSn;

    /** 需要锁住的所有库存信息 **/
    private List<OrderItemVo> locks;
}
