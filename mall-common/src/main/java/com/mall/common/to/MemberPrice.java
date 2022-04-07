package com.mall.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 会员等级实体
 * @author littlecheung
 */
@Data
public class MemberPrice {

    private Long id;

    private String name;

    private BigDecimal price;
}
