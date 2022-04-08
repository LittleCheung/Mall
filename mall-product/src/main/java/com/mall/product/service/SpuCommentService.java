package com.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.common.utils.PageUtils;
import com.mall.product.entity.SpuCommentEntity;

import java.util.Map;

/**
 * 商品评价
 *
 * @author littlecheung
 */
public interface SpuCommentService extends IService<SpuCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

