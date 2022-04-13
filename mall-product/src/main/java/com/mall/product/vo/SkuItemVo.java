package com.mall.product.vo;

import com.mall.product.entity.SkuImagesEntity;
import com.mall.product.entity.SkuInfoEntity;
import com.mall.product.entity.SpuInfoDescEntity;
import lombok.Data;

import java.util.List;

/**
 * sku商品项实体
 * @author littlecheung
 */
@Data
public class SkuItemVo {

    /**
     * sku基本信息获取 pms_sku_info
     */
    SkuInfoEntity info;
    /**
     * 是否有库存
     */
    boolean hasStock = true;
    /**
     * sku图片信息    pms_sku_images
     */
    List<SkuImagesEntity> images;
    /**
     * 获取spu的销售属性组合
     */
    List<SkuItemSaleAttrVo> saleAttr;
    /**
     * 获取spu的介绍
     */
    SpuInfoDescEntity desp;
    /**
     * 获取spu的规格参数信息
     */
    List<SpuItemAttrGroupVo> groupAttrs;
    /**
     * 当前秒杀商品的优惠信息
     */
    private SeckillSkuVo seckillSkuVo;


}
