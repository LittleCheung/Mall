package com.mall.ware.vo;

import lombok.Data;


/**
 *
 * @author littlecheung
 */
@Data
public class PurchaseItemDoneVo {

    private Long itemId;

    private Integer status;

    private String reason;

}
