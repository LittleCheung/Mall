package com.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.common.to.OrderTo;
import com.mall.common.to.mq.StockLockedTo;
import com.mall.common.utils.PageUtils;
import com.mall.ware.entity.WareSkuEntity;
import com.mall.ware.vo.SkuHasStockVo;
import com.mall.ware.vo.WareSkuLockVo;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author littlecheung
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 添加库存
     * @param skuId
     * @param wareId
     * @param skuNum
     */
    void addStock(Long skuId, Long wareId, Integer skuNum);

    /**
     * 判断是否有库存
     * @param skuIds
     * @return
     */
    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);

    /**
     * 锁定库存
     * @param vo
     * @return
     */
    boolean orderLockStock(WareSkuLockVo vo);


    /**
     * 解锁库存
     * @param to
     */
    void unlockStock(StockLockedTo to) throws IOException;

    /**
     * 解锁订单
     * @param orderTo
     */
    void unlockStockOrder(OrderTo orderTo);
}

