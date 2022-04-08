package com.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.product.entity.ProductAttrValueEntity;
import com.mall.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author littlecheung
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveProductAttr(List<ProductAttrValueEntity> collect);

    List<ProductAttrValueEntity> baseAttrlistforspu(Long spuId);

    void updateSpuAttr(Long spuId, List<ProductAttrValueEntity> entities);
}

