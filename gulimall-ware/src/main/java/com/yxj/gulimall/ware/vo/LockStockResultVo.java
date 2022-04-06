package com.yxj.gulimall.ware.vo;

import lombok.Data;

/**
 * @author yaoxinjia
 */
@Data
public class LockStockResultVo {

    private Long skuId;

    private Integer num;

    /** 是否锁定成功 **/
    private Boolean locked;

}
