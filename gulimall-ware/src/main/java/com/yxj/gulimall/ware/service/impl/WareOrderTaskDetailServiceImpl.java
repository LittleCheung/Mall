package com.yxj.gulimall.ware.service.impl;

import com.yxj.gulimall.common.utils.PageUtils;
import com.yxj.gulimall.common.utils.Query;
import com.yxj.gulimall.ware.dao.WareOrderTaskDetailDao;
import com.yxj.gulimall.ware.entity.WareOrderTaskDetailEntity;
import com.yxj.gulimall.ware.service.WareOrderTaskDetailService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @author yaoxinjia
 */
@Service("wareOrderTaskDetailService")
public class WareOrderTaskDetailServiceImpl extends ServiceImpl<WareOrderTaskDetailDao, WareOrderTaskDetailEntity> implements WareOrderTaskDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareOrderTaskDetailEntity> page = this.page(
                new Query<WareOrderTaskDetailEntity>().getPage(params),
                new QueryWrapper<WareOrderTaskDetailEntity>()
        );

        return new PageUtils(page);
    }

}