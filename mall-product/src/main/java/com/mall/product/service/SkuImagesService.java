package com.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxj.gulimall.common.utils.PageUtils;
import com.mall.product.entity.SkuImagesEntity;

import java.util.List;
import java.util.Map;

/**
 * sku图片
 *
 * @author yaoxinjia
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SkuImagesEntity> getImagesBySkuId(Long skuId);
}

