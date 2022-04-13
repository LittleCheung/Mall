package com.mall.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mall.auth.feign.MemberFeignService;
import com.mall.auth.vo.SocialUser;
import com.mall.common.constant.AuthServerConstant;
import com.mall.common.vo.MemberRespVo;
import com.mall.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 处理社交方式登录请求
 * @author littlecheung
 */
@Slf4j
@Controller
public class Oauth2Controller {

    @Autowired
    private MemberFeignService memberFeignService;

    /**
     * 使用微博登录
     * @param code 验证码
     * @param session 利用session原理，将数据放在session中，只要跳到下一个页面取出这个数据以后，session里面的数据就会被删掉
     * @return
     * @throws Exception
     */
    @GetMapping("/oauth2.0/weibo/success")
    public String weibo(@RequestParam("code") String code, HttpSession session) throws Exception {
        String url = "https://api.weibo.com/oauth2/access_token?client_id=1411893798&client_secret=6b03671f1d5bd30edcd63f029a38a428&grant_type=authorization_code&redirect_uri=http://auth.mall.com/oauth2.0/weibo/success&code=" +code;
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        HttpResponse response = httpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode()==200){
            //获取到accessToken，就知道当前是哪个社交用户登录成功
            String json = EntityUtils.toString(response.getEntity());
            System.out.println(response.getEntity());
            SocialUser socialUser = JSON.parseObject(json, SocialUser.class);

            // 当前用户如果是第一次进网站，就自动注册进来（为当前社交用户生成一个会员信息账号,以后这个社交账号就对应指定的会员）
            // 登录或者注册这个社交用户
            R r = memberFeignService.oauth2Login(socialUser);
            if (r.getCode()==0){
                MemberRespVo memberRespVo = r.getData(new TypeReference<MemberRespVo>() {});
                log.info("登录成功，用户信息：" + memberRespVo);
                //TODO 1 默认发的令牌 session=唯一字符串,作用域只是当前域（解决子域与父域session共享问题）
                //     解决办法：使用Spring Session整合Redis，在SessionConfig配置CookieSerializer放大作用域为赋予即可
                //TODO 2 之前每次都要实现Serializable序列化接口实现序列化（解决使用json的序列化方式来序列化对象数据到redis中的问题）
                //     解决办法：在SessionConfig配置RedisSerializer即可实现json序列化方式
                session.setAttribute(AuthServerConstant.SESSION_LOGIN_KEY,memberRespVo);
                // 登录成功就跳转回首页
                return "redirect:http://mall.com";
            }else {
                return "redirect:http://auth.mall.com/login.html";
            }
        }else{
            return "redirect:http://mall.com/login.html";
        }
    }
}
