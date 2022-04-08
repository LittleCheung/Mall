package com.mall.product.dao;

import com.mall.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 品牌分类关联
 * @author littlecheung
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {

    void updateCatogory(@Param("catId") Long catId, @Param("name") String name);
}
