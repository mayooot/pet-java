package com.hexu.petproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname AiliyunConfig
 * @Description  阿里云用户
 * @Author 86176
 * @Date 2022-04-05 10:21
 * @Version 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "aliyun")
public class AiliyunConfig {
    //访问身份验证中用到用户标识
    public static String ACCESSKEYID;
    //用户用于加密签名字符串和oss用来验证签名字符串的密钥
    public static String ACCESSKEYSECRET;


    private String accessKeyId;
    private String accessKeySecret;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
        ACCESSKEYID = this.accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
        ACCESSKEYSECRET = this.accessKeySecret;
    }
}
