package com.hexu.petproject.config;

import com.hexu.petproject.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Classname CostomWebMvcConfig
 * @Description Web相关的处理  拦截器的配置
 * @Author 86176
 * @Date 2022-04-08 11:04
 * @Version 1.0
 **/
@Configuration
public class CostomWebMvcConfig implements WebMvcConfigurer {
    @Resource
    private AuthInterceptor authInterceptor;

    /**
     * @param registry 自定义拦截请求
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("");
    }
}
