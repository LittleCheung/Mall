package com.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.product.entity.CategoryEntity;
import com.mall.product.vo.Catelog2Vo;
import com.mall.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author littlecheung
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查出所有分类以及子分类，以树形结构组装起来
     * @return
     */
    List<CategoryEntity> listWithTree();


    /**
     * 删除
     * @param asList
     */
    void removeMenuByIds(List<Long> asList);


    /**
     * 找到catelogId的完整路径：[父/子/孙]
     * @param catelogId
     * @return
     */
    Long[] findCatelogPath(Long catelogId);


    /**
     * 级联更新所有关联的数据
     * @param category
     * @return
     */
    void updateCasecade(CategoryEntity category);


    List<CategoryEntity> getLevelCategorys();


    Map<String, List<Catelog2Vo>> getCatalogJson() throws InterruptedException;
}

