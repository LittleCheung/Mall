package com.mall.coupon.service.impl;

import com.mall.coupon.dao.SeckillSkuRelationDao;
import com.mall.coupon.entity.SeckillSkuRelationEntity;
import com.mall.coupon.service.SeckillSkuRelationService;
import com.mall.common.utils.PageUtils;
import com.mall.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.util.StringUtils;


@Service("seckillSkuRelationService")
public class SeckillSkuRelationServiceImpl extends ServiceImpl<SeckillSkuRelationDao, SeckillSkuRelationEntity> implements SeckillSkuRelationService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<SeckillSkuRelationEntity> queryWrapper = new QueryWrapper<SeckillSkuRelationEntity>();
        //获取key
        String key = (String) params.get("key");
        //获取场次id
        String promotionSessionId = (String) params.get("promotionSessionId");

        if (!StringUtils.isEmpty(key)) {
            queryWrapper.eq("id", key);
        }
        if (!StringUtils.isEmpty(promotionSessionId)) {
            queryWrapper.eq("promotion_session_id", promotionSessionId);
        }
        IPage<SeckillSkuRelationEntity> page = this.page(
                new Query<SeckillSkuRelationEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }
}