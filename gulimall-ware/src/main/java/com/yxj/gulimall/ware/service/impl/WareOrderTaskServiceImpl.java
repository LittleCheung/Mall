package com.yxj.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxj.gulimall.common.utils.PageUtils;
import com.yxj.gulimall.common.utils.Query;
import com.yxj.gulimall.ware.dao.WareOrderTaskDao;
import com.yxj.gulimall.ware.entity.WareOrderTaskEntity;
import com.yxj.gulimall.ware.service.WareOrderTaskService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author yaoxinjia
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

    @Override
    public WareOrderTaskEntity getOrderTaskByOrderSn(String orderSn) {

        WareOrderTaskEntity orderTaskEntity = this.baseMapper.selectOne(
                new QueryWrapper<WareOrderTaskEntity>().eq("order_sn", orderSn));

        return orderTaskEntity;
    }

}