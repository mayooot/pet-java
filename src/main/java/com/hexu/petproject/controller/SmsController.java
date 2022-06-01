package com.hexu.petproject.controller;

import com.hexu.petproject.interceptor.Authentication;
import com.hexu.petproject.service.SmsService;
import com.hexu.petproject.util.ResultVO;
import com.hexu.petproject.util.ResultEnum;
import com.hexu.petproject.util.SmsUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Classname SmsController
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-05 11:53
 * @Version 1.0
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/ums/sms")
public class SmsController {
    @Resource
    private SmsService smsService;



    /**
     * 发送验证码
     *
     * @param phone    手机号
     * @param codeType 类型
     * @return
     */
    @Authentication
    @PostMapping("/sendSms")
    public ResultVO sendSms(String phone, Integer codeType) {
        //验证手机号格式
        if (!SmsUtils.checkPhone(phone)) {
            return ResultVO.errer(ResultEnum.MALFORMED_PHONE_NUMBER);
        }
        //发送验证码
        Boolean resultBool = smsService.sendSms(phone, codeType);
        return ResultVO.ok();
    }

}
