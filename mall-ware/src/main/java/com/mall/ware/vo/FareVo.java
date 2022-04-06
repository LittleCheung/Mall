package com.mall.ware.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yaoxinjia
 */
@Data
public class FareVo {

    private MemberAddressVo address;

    private BigDecimal fare;

}


