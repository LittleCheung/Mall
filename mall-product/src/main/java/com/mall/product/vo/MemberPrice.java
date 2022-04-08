package com.mall.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @author littlecheung
 */
@Data
public class MemberPrice {

    private Long id;

    private String name;

    private BigDecimal price;


}