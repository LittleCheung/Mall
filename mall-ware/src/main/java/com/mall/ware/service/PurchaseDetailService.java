package com.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.ware.entity.PurchaseDetailEntity;
import com.yxj.gulimall.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @author yaoxinjia
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PurchaseDetailEntity> listDetailByPurchaseId(Long id);
}

