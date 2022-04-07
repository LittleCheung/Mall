package com.mall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * 解决跨域问题的配置类
 * @author littlecheung
 * gateway
 */
@Configuration
public class CorsConfig {

    /**
     * 添加跨域过滤器
     * @return
     */
    @Bean
    public CorsWebFilter corsWebFilter(){
        // 基于url跨域，选择reactive包下的
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 跨域配置信息
        org.springframework.web.cors.CorsConfiguration corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
        // 允许跨域的头
        corsConfiguration.addAllowedHeader("*");
        // 允许跨域的请求方式
        corsConfiguration.addAllowedMethod("*");
        // 允许跨域的请求来源
        corsConfiguration.addAllowedOrigin("*");
        // 是否允许携带cookie跨域
        corsConfiguration.setAllowCredentials(true);
       // 任意url都要进行跨域配置
        source.registerCorsConfiguration("/**",corsConfiguration);

        return new CorsWebFilter(source);
    }
}
