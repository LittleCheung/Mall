package com.yxj.gulimall.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @author yaoxinjia
 */
@Data
public class SpuBoundTo {
    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
