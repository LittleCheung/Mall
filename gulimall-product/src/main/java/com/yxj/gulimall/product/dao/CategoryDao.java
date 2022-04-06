package com.yxj.gulimall.product.dao;

import com.yxj.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author yaoxinjia
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
