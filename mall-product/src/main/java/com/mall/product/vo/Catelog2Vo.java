package com.mall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 二级分类实体
 * @author littlecheung
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Catelog2Vo {

    // 一级父分类id
    private String catalog1Id;

    // 三级子分类
    private List<Catelog3Vo> catalog3List;

    private String id;

    private String name;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static  class Catelog3Vo{

        // 父分类是二级分类id
        private String catalog2Id;

        private String id;

        private String name;
    }




}
