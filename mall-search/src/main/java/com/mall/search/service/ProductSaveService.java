package com.mall.search.service;

import com.mall.common.to.es.SkuEsModel;

import java.util.List;

/**
 *
 * @author littlecheung
 */
public interface ProductSaveService {

    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws Exception;
}
