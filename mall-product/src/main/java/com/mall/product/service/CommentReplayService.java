package com.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.product.entity.CommentReplayEntity;
import com.mall.common.utils.PageUtils;

import java.util.Map;

/**
 * 商品评价回复关系
 *
 * @author littlecheung
 */
public interface CommentReplayService extends IService<CommentReplayEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

