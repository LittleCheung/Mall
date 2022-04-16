package com.mall.product.service.impl;

import com.mall.product.entity.ProductAttrValueEntity;
import com.mall.common.utils.PageUtils;
import com.mall.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.mall.product.dao.ProductAttrValueDao;
import com.mall.product.service.ProductAttrValueService;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author littlecheung
 */
@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity> implements ProductAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        IPage<ProductAttrValueEntity> page = this.page(
                new Query<ProductAttrValueEntity>().getPage(params),
                new QueryWrapper<ProductAttrValueEntity>()
        );
        return new PageUtils(page);
    }


    @Override
    public void saveProductAttr(List<ProductAttrValueEntity> collect) {
        this.saveBatch(collect);
    }

    /**
     *获取spu商品的规格属性
     * @param spuId
     * @return
     */
    @Override
    public List<ProductAttrValueEntity> baseAttrlistforspu(Long spuId) {

        List<ProductAttrValueEntity> entities = this.baseMapper.selectList(new QueryWrapper<ProductAttrValueEntity>()
                .eq("spu_id", spuId));
        return entities;
    }

    /**
     * 更新spu商品属性
     * @param spuId
     * @param entities
     */
    @Transactional
    @Override
    public void updateSpuAttr(Long spuId, List<ProductAttrValueEntity> entities) {

        //先删除这个spuId之前对应的所有属性
        this.baseMapper.delete(new QueryWrapper<ProductAttrValueEntity>().eq("spu_id", spuId));
        //更新商品属性
        List<ProductAttrValueEntity> collect = entities.stream().map((item) -> {
            item.setSpuId(spuId);
            return item;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }
}