package com.mall.common.to;

import lombok.Data;

/**
 * 库存量单位是否有库存实体
 * @author littlecheung
 */
@Data
public class SkuHasStockTo {

    private Long skuId;

    private Boolean hasStock;
}
