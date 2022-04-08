package com.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.product.vo.AttrGroupRelationVo;
import com.mall.product.vo.AttrVo;
import com.mall.common.utils.PageUtils;
import com.mall.product.entity.AttrEntity;
import com.mall.product.vo.AttrRespVo;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author littlecheung
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void deleteRelation(AttrGroupRelationVo[] vos);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);


    /**
     * 在指定的所有属性集合里面，挑出检索属性
     * @param attrIds
     * @return
     */
    List<Long> selectSearchAttrIds(List<Long> attrIds);
}

