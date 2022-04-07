package com.mall.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 库存量单位促销实体
 * @author littlecheung
 */
@Data
public class SkuReductionTo {

    private Long skuId;

    private int fullCount;

    private BigDecimal discount;

    private int countStatus;

    private BigDecimal fullPrice;

    private BigDecimal reducePrice;

    private int priceStatus;

    private List<MemberPrice> memberPrice;
}
