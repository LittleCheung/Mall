package com.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.ware.dao.WareOrderTaskDao;
import com.mall.ware.entity.WareOrderTaskEntity;
import com.mall.ware.service.WareOrderTaskService;
import com.mall.common.utils.PageUtils;
import com.mall.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *
 * @author littlecheung
 */
@Service("wareOrderTaskService")
public class WareOrderTaskServiceImpl extends ServiceImpl<WareOrderTaskDao, WareOrderTaskEntity> implements WareOrderTaskService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareOrderTaskEntity> page = this.page(
                new Query<WareOrderTaskEntity>().getPage(params),
                new QueryWrapper<WareOrderTaskEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 根据订单号获取库存工作单
     * @param orderSn
     * @return
     */
    @Override
    public WareOrderTaskEntity getOrderTaskByOrderSn(String orderSn) {

        WareOrderTaskEntity orderTaskEntity = this.baseMapper.selectOne(
                new QueryWrapper<WareOrderTaskEntity>().eq("order_sn", orderSn));
        return orderTaskEntity;
    }

}