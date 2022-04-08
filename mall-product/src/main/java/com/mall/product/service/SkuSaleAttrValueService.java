package com.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.mall.product.entity.SkuSaleAttrValueEntity;
import com.mall.product.vo.SkuItemSaleAttrVo;
import com.mall.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author littlecheung
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SkuItemSaleAttrVo> getSaleAttrsBySpuId(Long spuId);

    List<String> getSkuSaleAttrValuesAsStringList(Long skuId);
}

