package com.yxj.gulimall.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;
/**
 * @author yaoxinjia
 */
@ToString
@Data
public class SpuItemAttrGroupVo {
    private String groupName;
    private List<Attr> attrs;
}
