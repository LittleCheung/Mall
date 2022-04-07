package com.mall.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 标准产品单位实体
 * @author littlecheung
 */
@Data
public class SpuBoundTo {

    private Long spuId;

    private BigDecimal buyBounds;

    private BigDecimal growBounds;
}
