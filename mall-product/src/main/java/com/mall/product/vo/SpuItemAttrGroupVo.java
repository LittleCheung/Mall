package com.mall.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 *
 * @author littlecheung
 */
@ToString
@Data
public class SpuItemAttrGroupVo {

    private String groupName;

    private List<Attr> attrs;
}
