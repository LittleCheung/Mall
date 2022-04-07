package com.mall.auth.feign;

import com.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 第三方服务远程调用配置接口
 * @author littlecheung
 */
@FeignClient("mall-third-party")
public interface ThirdPartyFeignService {

    @GetMapping("/sms/sendCode")
    public R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code);
}
