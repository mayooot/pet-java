package com.hexu.petproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname AiliyunSmsConfig
 * @Description 阿里云短信发送服务
 * @Author 86176
 * @Date 2022-04-05 10:27
 * @Version 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "aliyun.sms")
public class AiliyunSmsConfig extends AiliyunConfig{

    public static String SIGNNAME;
    public static String ENDPOINT;
    public static String TEMPLATECODE;


    private String signName;
    private String endpoInt;
    private String templateCode;

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
        SIGNNAME = this.signName;
    }

    public String getEndpoInt() {
        return endpoInt;
    }

    public void setEndpoInt(String endpoInt) {
        this.endpoInt = endpoInt;
        ENDPOINT = this.endpoInt;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
        TEMPLATECODE = this.templateCode;
    }
}
