package com.mall.product.vo;

import com.mall.product.entity.SkuImagesEntity;
import com.mall.product.entity.SkuInfoEntity;
import com.mall.product.entity.SpuInfoDescEntity;
import lombok.Data;

import java.util.List;

/**
 *
 * @author littlecheung
 */
@Data
public class SkuItemVo {

    //1、sku基本信息获取 pms_sku_info
    SkuInfoEntity info;

    boolean hasStock = true;//库存
    //2、sku图片信息    pms_sku_images
    List<SkuImagesEntity> images;
    //3、获取spu的销售属性组合
    List<SkuItemSaleAttrVo> saleAttr;
    //4、获取spu的介绍
    SpuInfoDescEntity desp;
    //5、获取spu的规格参数信息
    List<SpuItemAttrGroupVo> groupAttrs;

    //6、秒杀商品的优惠信息
    private SeckillSkuVo seckillSkuVo;


}
