package com.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.product.vo.AttrGroupRelationVo;
import com.mall.common.utils.PageUtils;
import com.mall.product.entity.AttrAttrgroupRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author littlecheung
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveBatch(List<AttrGroupRelationVo> vos);
}

