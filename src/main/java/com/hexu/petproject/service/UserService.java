package com.hexu.petproject.service;

import com.hexu.petproject.model.pojo.User;
import com.hexu.petproject.model.vo.userVO.UserVO;

/**
 * @Classname UserService
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-06 15:27
 * @Version 1.0
 **/
public interface UserService
{
    /**
     *  判断是否注册过  否就注册  true 登录
     * @param phone 手机号
     * @param sms 验证码
     * @return
     */
    String clickSend(String phone, String sms);

    /**
     * 根据请求头的uuid获取redis的用户信息
     * @param token uuid
     * @return
     */
    UserVO getUUID(String token);
}
