package com.mall.product.vo;

import lombok.Data;

/**
 * @author littlecheung
 */
@Data
public class AttrRespVo extends AttrVo{

    private String catelogName;

    private String groupName;

    private Long[] catelogPath;
}
