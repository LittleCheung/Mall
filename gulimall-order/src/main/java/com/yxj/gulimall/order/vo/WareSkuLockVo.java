package com.yxj.gulimall.order.vo;

import lombok.Data;

import java.util.List;
/**
 * @author yaoxinjia
 * @email 894548575@qq.com
 */
@Data
public class WareSkuLockVo {

    private String orderSn;

    /** 需要锁住的所有库存信息 **/
    private List<OrderItemVo> locks;



}
