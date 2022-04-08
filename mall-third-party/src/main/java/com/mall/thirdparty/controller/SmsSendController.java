package com.mall.thirdparty.controller;

import com.mall.common.utils.R;
import com.mall.thirdparty.component.SmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author littlecheung
 */
@RestController
@RequestMapping("/sms")
public class SmsSendController {

    @Autowired
    private SmsComponent smsComponent;

    /**
     * 发送验证码
     * @param phone
     * @param code
     * @return
     */
    @GetMapping("sendCode")
    public R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code){
        smsComponent.sengSmsCode(phone,code);
        return R.ok();
    }
}
