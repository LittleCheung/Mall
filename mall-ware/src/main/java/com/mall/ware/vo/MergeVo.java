package com.mall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * 合并采购单实体
 * @author littlecheung
 */
@Data
public class MergeVo {

    private Long purchaseId;

    private List<Long> items;

}
