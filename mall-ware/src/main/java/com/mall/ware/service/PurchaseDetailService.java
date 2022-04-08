package com.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.ware.entity.PurchaseDetailEntity;
import com.mall.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 *
 * @author littlecheung
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PurchaseDetailEntity> listDetailByPurchaseId(Long id);
}

