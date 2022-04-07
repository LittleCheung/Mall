package com.mall.common.to.es;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Sku在Elasticsearch中保存的数据实体
 * @author littlecheung
 */
@Data
public class SkuEsModel {

    private Long skuId;

    private Long spuId;

    private String skuTitle;

    private BigDecimal skuPrice;

    private String skuImg;

    private Long saleCount;

    private Boolean hasStock;

    private Long hotScore;

    private Long brandId;

    private Long catalogId;

    private String brandName;

    private String brandImg;

    private String catalogName;

    private List<Attrs> attrs;

    @Data
    public static class Attrs{
        private Long attrId;

        private String attrName;

        private String attrValue;
    }
}
