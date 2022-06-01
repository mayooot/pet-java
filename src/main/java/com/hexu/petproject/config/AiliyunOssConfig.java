package com.hexu.petproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname OssProperties
 * @Description  阿里云Oss服务
 * @Author 86176
 * @Date 2022-04-02 14:01
 * @Version 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class AiliyunOssConfig extends AiliyunConfig{
    // oss对外服务的访问域名
    public static String ENDPOINT;
    //oss的存储空间
    public static String BUCKETNAME;
    //上传文件夹路径前缀
    public static String PREFIX;

    /*
    *   动态获取
    */
    private String endpoint;
    private String bucketName;
    private String prefix;


    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        ENDPOINT = this.endpoint;
    }
    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
        BUCKETNAME = this.bucketName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
        PREFIX = this.prefix;
    }
}
