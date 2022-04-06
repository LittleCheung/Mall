package com.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxj.gulimall.common.utils.PageUtils;
import com.mall.product.entity.SpuCommentEntity;

import java.util.Map;

/**
 * 商品评价
 *
 * @author yaoxinjia
 */
public interface SpuCommentService extends IService<SpuCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

