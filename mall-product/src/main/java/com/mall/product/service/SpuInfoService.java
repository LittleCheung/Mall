package com.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.product.entity.SpuInfoEntity;
import com.mall.product.vo.SpuSaveVo;
import com.mall.common.utils.PageUtils;

import java.util.Map;

/**
 * spu信息
 *
 * @author littlecheung
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void savaSpuInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfoEntity infoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);


    /**
     * 根据spuId进行商品上架
     * @param spuId
     */
    void up(Long spuId);

    /**
     * 根据skuId获取spu信息
     * @param skuId
     * @return
     */
    SpuInfoEntity getSpuInfoBySkuId(Long skuId);
}

