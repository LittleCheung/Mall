package com.mall.product.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.mall.product.service.*;
import com.mall.product.vo.SkuItemVo;
import com.mall.product.vo.SpuItemAttrGroupVo;
import com.mall.common.utils.PageUtils;
import com.mall.common.utils.Query;
import com.mall.common.utils.R;
import com.mall.product.entity.SkuImagesEntity;
import com.mall.product.entity.SpuInfoDescEntity;
import com.mall.product.feign.SeckillFeignService;
import com.mall.product.vo.SeckillSkuVo;
import com.mall.product.vo.SkuItemSaleAttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.mall.product.dao.SkuInfoDao;
import com.mall.product.entity.SkuInfoEntity;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 *
 * @author littlecheung
 */
@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {


    @Autowired
    private SkuImagesService imagesService;

    @Autowired
    private SpuInfoDescService spuInfoDescService;

    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private ThreadPoolExecutor executor;


    @Resource
    private SeckillFeignService seckillFeignService;

    /**
     * 简单分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 保存sku信息
     * @param skuInfoEntity
     */
    @Override
    public void saveSkuInfo(SkuInfoEntity skuInfoEntity) {

        this.baseMapper.insert(skuInfoEntity);
    }

    /**
     * 条件分页查询sku信息
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {

        QueryWrapper<SkuInfoEntity> queryWrapper = new QueryWrapper<>();
        //模糊查询条件
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and((wrapper) ->{
                wrapper.eq("sku_id", key).or().like("sku_name", key);
            });
        }
        String catelogId = (String) params.get("catelogId");
        if (!StringUtils.isEmpty(catelogId) && !"0".equalsIgnoreCase(catelogId)) {

            queryWrapper.eq("catalog_id", catelogId);
        }
        String brandId = (String) params.get("brandId");
        if (!StringUtils.isEmpty(brandId) && !"0".equalsIgnoreCase(catelogId)) {
            queryWrapper.eq("brand_id", brandId);
        }
        String min = (String) params.get("min");
        if (!StringUtils.isEmpty(min)) {
            queryWrapper.ge("price", min);
        }
        String max = (String) params.get("max");
        if (!StringUtils.isEmpty(max)) {
            try {
                BigDecimal bigDecimal = new BigDecimal(max);
                if (bigDecimal.compareTo(new BigDecimal("0")) == 1) {
                    queryWrapper.le("price", max);
                }
            } catch (Exception e) {

            }
            queryWrapper.le("price", max);
        }
        IPage<SkuInfoEntity> page = this.page(new Query<SkuInfoEntity>().getPage(params),queryWrapper);
        return new PageUtils(page);
    }

    /**
     *
     * @param spuId
     * @return
     */
    @Override
    public List<SkuInfoEntity> getSkusBySpuId(Long spuId) {
        List<SkuInfoEntity> list = this.list(new QueryWrapper<SkuInfoEntity>().eq("spu_id",spuId));
        return list;
    }

    /**
     * 展示当前sku的详情
     * @param skuId
     * @return
     */
    @Override
    public SkuItemVo item(Long skuId) {
        //TODO CompletableFuture异步编排任务提交给线程池
        SkuItemVo skuItemVo = new SkuItemVo();
        //获取sku基本信息
        CompletableFuture<SkuInfoEntity> infoFuture = CompletableFuture.supplyAsync(() -> {
            SkuInfoEntity info = getById(skuId);
            skuItemVo.setInfo(info);
            return info;
        }, executor);
        //获取spu的销售属性组合(接收infoFuture的结果)
        CompletableFuture<Void> saleAttrFuture = infoFuture.thenAcceptAsync((res) -> {
            List<SkuItemSaleAttrVo> saleAttrVos = skuSaleAttrValueService.getSaleAttrsBySpuId(res.getSpuId());
            skuItemVo.setSaleAttr(saleAttrVos);
        }, executor);
        //获取spu的介绍信息
        CompletableFuture<Void> descFuture = infoFuture.thenAcceptAsync(res -> {
            SpuInfoDescEntity spuInfoDescEntity = spuInfoDescService.getById(res.getSpuId());
            skuItemVo.setDesp(spuInfoDescEntity);
        }, executor);
        //获取spu的规格参数信息
        CompletableFuture<Void> attrGroupFuture = infoFuture.thenAcceptAsync(res -> {
            List<SpuItemAttrGroupVo> attrGroupVos = attrGroupService.getAttrGroupWithAttrsBySpuId(res.getSpuId(), res.getCatalogId());
            skuItemVo.setGroupAttrs(attrGroupVos);
        }, executor);

        //获取sku图片信息
        CompletableFuture<Void> imgFuture = CompletableFuture.runAsync(() -> {
            List<SkuImagesEntity> skuImagesEntityList = imagesService.getImagesBySkuId(skuId);
            skuItemVo.setImages(skuImagesEntityList);
        }, executor);
        //获取秒杀信息
        CompletableFuture<Void> seckillFuture = CompletableFuture.runAsync(() -> {
            //TODO 远程调用服务查询当前sku是否参与秒杀优惠活动
            R skuSeckillInfo = seckillFeignService.getSkuSeckilInfo(skuId);
            if (skuSeckillInfo.getCode() == 0) {
                SeckillSkuVo seckillInfoData = skuSeckillInfo.getData("data", new TypeReference<SeckillSkuVo>() {});
                skuItemVo.setSeckillSkuVo(seckillInfoData);
                if (seckillInfoData != null) {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime > seckillInfoData.getEndTime()) {
                        skuItemVo.setSeckillSkuVo(null);
                    }
                }
            }
        }, executor);
        try {
            //TODO 等待所有异步任务都完成后才返回
            CompletableFuture.allOf(saleAttrFuture,descFuture,attrGroupFuture,imgFuture,seckillFuture).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return skuItemVo;
    }
}