package com.hexu.petproject.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname ThreadPool
 * @Description 动态获取yml 线程池配置值
 * @Author 86176
 * @Date 2022-04-03 21:43
 * @Version 1.0
 **/
@Configuration
@Data
@ConfigurationProperties("threadpoolexecutor.thread")
public class ThreadPoolConfig {
    private Integer  coreSize;
    private Integer  maxSize;
    private Integer  keepAliveTime;
}
