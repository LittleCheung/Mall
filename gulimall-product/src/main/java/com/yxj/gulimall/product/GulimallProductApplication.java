package com.yxj.gulimall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
/**
 * @author yaoxinjia
 */
@EnableRedisHttpSession
@EnableFeignClients(basePackages = "com.yxj.gulimall.product.feign")
@EnableDiscoveryClient
@MapperScan("com.yxj.gulimall.product.dao")
@SpringBootApplication
public class GulimallProductApplication {

    /**
     * @Cacheable 触发将数据保存到缓存中的操作
     * @CacheEvict 触发将数据从缓存删除的操作
     * @CachePut
     * @Caching 组合以上多个操作
     * @CacheConfig
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}
