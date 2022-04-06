package com.yxj.gulimall.search.service;


import com.yxj.gulimall.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;
/**
 * @author yaoxinjia
 */
public interface ProductSaveService {
    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws Exception;
}
