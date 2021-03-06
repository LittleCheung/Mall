package com.mall.ware.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 采购完成实体
 * @author littlecheung
 */
@Data
public class PurchaseDoneVo {

    @NotNull(message = "id不允许为空")
    private Long id;

    private List<PurchaseItemDoneVo> items;

}
