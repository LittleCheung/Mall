package com.mall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @author littlecheung
 */
@Data
public class FareVo {

    private MemberAddressVo address;

    private BigDecimal fare;

}
