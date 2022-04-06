package com.yxj.gulimall.order.vo;

import lombok.Data;

import java.math.BigDecimal;
/**
 * @author yaoxinjia
 * @email 894548575@qq.com
 */
@Data
public class FareVo {

    private MemberAddressVo address;

    private BigDecimal fare;

}
