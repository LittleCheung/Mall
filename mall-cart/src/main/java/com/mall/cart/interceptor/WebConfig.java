package com.mall.cart.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 拦截器相关配置
 * @author littlecheung
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        registry.addInterceptor(new CartInterceptor())
                .addPathPatterns("/**");
    }
}
