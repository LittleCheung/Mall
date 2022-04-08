package com.mall.product.feign;

import com.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 仓储模块远程调用接口
 * @author littlecheung
 */
@FeignClient("mall-ware")
public interface WareFeignService {


    @PostMapping("/ware/waresku/hasStock")
    R getSkuHasStock(@RequestBody List<Long> SkuIds);
}
