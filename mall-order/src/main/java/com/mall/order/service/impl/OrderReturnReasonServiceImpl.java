package com.mall.order.service.impl;

import com.mall.order.dao.OrderReturnReasonDao;
import com.mall.common.utils.PageUtils;
import com.mall.common.utils.Query;
import com.mall.order.entity.OrderReturnReasonEntity;
import com.mall.order.service.OrderReturnReasonService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 *
 * @author littlecheung
 */
@Service("orderReturnReasonService")
public class OrderReturnReasonServiceImpl extends ServiceImpl<OrderReturnReasonDao, OrderReturnReasonEntity> implements OrderReturnReasonService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderReturnReasonEntity> page = this.page(
                new Query<OrderReturnReasonEntity>().getPage(params),
                new QueryWrapper<OrderReturnReasonEntity>()
        );
        return new PageUtils(page);
    }
}