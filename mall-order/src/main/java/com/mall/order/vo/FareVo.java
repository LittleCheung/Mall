package com.mall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 运费实体
 * @author littlecheung
 */
@Data
public class FareVo {

    private MemberAddressVo address;

    private BigDecimal fare;
}
