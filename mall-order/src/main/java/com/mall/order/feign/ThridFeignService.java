package com.mall.order.feign;

import com.alipay.api.AlipayApiException;
import com.mall.order.vo.PayVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 第三方服务模块远程调用接口
 * @author littlecheung
 */
@FeignClient("mall-third-party")
public interface ThridFeignService {

    @GetMapping(value = "/pay",consumes = "application/json")
    String pay(@RequestBody PayVo vo) throws AlipayApiException;

}
