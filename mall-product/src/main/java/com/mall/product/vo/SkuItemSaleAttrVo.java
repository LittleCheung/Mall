package com.mall.product.vo;

import lombok.Data;

import java.util.List;

/**
 *
 * @author littlecheung
 */
@Data
public class SkuItemSaleAttrVo {

    private Long attrId;

    private String attrName;

    private List<AttrValueWithSkuIdVo> attrValues;
}
