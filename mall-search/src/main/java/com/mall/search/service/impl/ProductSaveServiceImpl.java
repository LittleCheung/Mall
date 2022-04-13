package com.mall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.mall.search.config.ElasticSearchConfig;
import com.mall.search.constant.EsConstant;
import com.mall.common.to.es.SkuEsModel;
import com.mall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品信息保存到Elasticsearch
 * @author littlecheung
 */
@Service
@Slf4j
public class ProductSaveServiceImpl implements ProductSaveService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * 保存商品信息到ES
     * @param skuEsModels
     * @return
     * @throws Exception
     */
    @Override
    public boolean productStatusUp(List<SkuEsModel> skuEsModels) throws Exception {
        // 1 先在es中建立索引，product，建立映射关系


        BulkRequest bulkRequest = new BulkRequest();
        // 构造保存请求
        for (SkuEsModel model : skuEsModels) {
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(model.getSkuId().toString());
            String str = JSON.toJSONString(model);
            indexRequest.source(str, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        //2 es中批量保存这些数据
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, ElasticSearchConfig.COMMON_OPTIONS);

        // TODO 是否出现批量错误
        boolean b = bulk.hasFailures();
        List<String> collect = Arrays.stream(bulk.getItems()).map(item -> {
            return item.getId();
        }).collect(Collectors.toList());

        log.info("商品上架完成: {}, 返回数据: {}", collect, bulk);
        return  b;
    }
}
