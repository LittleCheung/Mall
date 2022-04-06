package com.mall.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
/**
 * @author yaoxinjia
 * @email 894548575@qq.com
 */
@EnableRedisHttpSession
@MapperScan("com.mall.member.dao")
@EnableDiscoveryClient
@EnableFeignClients(basePackages="com.mall.member.feign")
@SpringBootApplication
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }

}
