package com.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxj.gulimall.common.utils.PageUtils;
import com.mall.product.entity.BrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌
 *
 * @author yaoxinjia
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateDetail(BrandEntity brand);

    List<BrandEntity> getBrandsByIds(List<Long> brandIds);
}

