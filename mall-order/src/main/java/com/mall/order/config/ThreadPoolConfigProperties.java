package com.mall.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 *
 * @author littlecheung
 */
@ConfigurationProperties(prefix = "mall.thread")
@Data
public class ThreadPoolConfigProperties {

    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAliveTime;


}
