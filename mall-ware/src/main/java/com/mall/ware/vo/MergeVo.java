package com.mall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 *
 * @author littlecheung
 */
@Data
public class MergeVo {

    private Long purchaseId;

    private List<Long> items;

}
