package com.hexu.petproject.config;

/**
 * @Classname TaskPoolConfig
 * @Description  线程池
 * @Author 86176
 * @Date 2022-01-07 14:34
 * @Version 1.0
 **/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 定义异步任务执行线程池
 * @author 86176
 */
@Configuration
public class MyThreadPoolConfig {
    @Bean
    public ThreadPoolExecutor threadPoolExecutor (ThreadPoolConfig threadPoolConfig) {

        return new ThreadPoolExecutor(threadPoolConfig.getCoreSize()
                , threadPoolConfig.getMaxSize()
                , threadPoolConfig.getKeepAliveTime()
                , TimeUnit.SECONDS
                ,new LinkedBlockingDeque<>(100000)
                , Executors.defaultThreadFactory()
                ,new ThreadPoolExecutor.AbortPolicy());
    }
}

