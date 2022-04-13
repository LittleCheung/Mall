package com.mall.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 无库存时执行异常处理类
 * @author littlecheung
 */
@Getter
@Setter
public class NoStockException extends RuntimeException {

    private Long skuId;

    public NoStockException(Long skuId) {
        super("商品id："+ skuId + "库存不足！");
    }

    public NoStockException(String msg) {
        super(msg);
    }
}
