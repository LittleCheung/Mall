package com.mall.common.to.mq;

import lombok.Data;

/**
 * 库存锁定成功发送实体
 * @author littlecheung
 */
@Data
public class StockLockedTo {

    /** 库存工作单的id **/
    private Long id;

    /** 工作单详情的所有信息 **/
    private StockDetailTo detailTo;
}
