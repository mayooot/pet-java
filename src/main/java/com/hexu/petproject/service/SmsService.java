package com.hexu.petproject.service;

/**
 * @Classname SmsService
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-05 12:27
 * @Version 1.0
 **/
public interface SmsService {
    /**
     * 发送验证码
     * @param phone 手机号
     * @param codeType 类型
     * @return true
     */
    Boolean sendSms(String phone,Integer codeType);
}
