package com.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.product.entity.AttrGroupEntity;
import com.mall.product.vo.SpuItemAttrGroupVo;
import com.mall.common.utils.PageUtils;
import com.mall.product.vo.AttrGroupWithAttrsVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author littlecheung
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long catelogId);

    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId);

    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId);
}

