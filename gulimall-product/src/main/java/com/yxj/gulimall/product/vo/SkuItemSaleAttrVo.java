package com.yxj.gulimall.product.vo;

import lombok.Data;

import java.util.List;
/**
 * @author yaoxinjia
 */
@Data
public class SkuItemSaleAttrVo {
    private Long attrId;
    private String attrName;
    private List<AttrValueWithSkuIdVo> attrValues;
}
