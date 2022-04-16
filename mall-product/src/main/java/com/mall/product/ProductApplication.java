package com.mall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 商品服务启动类
 * @author littlecheung
 *
 * 补充信息：sku是库存量单位，spu是标准产品单位
 * 可以理解为：spu是商品聚合信息的最小单位，如苹果13；而sku是商品的不可再分的最小单位，如128G玫瑰金苹果13
 */
@EnableCaching
@EnableRedisHttpSession
@EnableFeignClients(basePackages = "com.mall.product.feign")
@EnableDiscoveryClient
@MapperScan("com.mall.product.dao")
@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
