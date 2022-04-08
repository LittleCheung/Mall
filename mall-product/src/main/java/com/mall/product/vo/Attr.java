/**
  * Copyright 2021 bejson.com 
  */
package com.mall.product.vo;

import lombok.Data;
import lombok.ToString;


/**
 *
 * @author littlecheung
 */
@ToString
@Data
public class Attr {

    private Long attrId;

    private String attrName;

    private String attrValue;

}