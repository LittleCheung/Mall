package com.mall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author littlecheung
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Catelog2Vo {
    // 1级父分类id
    private String catalog1Id;
    //  三级子分类
    private List<Catelog3Vo> catalog3List;

    private String id;

    private String name;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static  class Catelog3Vo{
        private String catalog2Id; // 父分类，2级分类id
        private String id;
        private String name;
    }




}
