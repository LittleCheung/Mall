package com.mall.cart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 线程池参数配置类
 * @author littlecheung
 */
@ConfigurationProperties(prefix = "mall.thread")
@Data
public class ThreadPoolConfigProperties {

    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAliveTime;


}
