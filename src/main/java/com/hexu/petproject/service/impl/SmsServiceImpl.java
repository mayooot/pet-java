package com.hexu.petproject.service.impl;

import com.hexu.petproject.exception.UmsException;
import com.hexu.petproject.service.SmsService;
import com.hexu.petproject.util.RedisUtils;
import com.hexu.petproject.util.ResultEnum;
import com.hexu.petproject.util.SmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Classname SmsServiceImpl
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-05 12:28
 * @Version 1.0
 **/
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发送验证码
     *
     * @param phone    手机号
     * @param codeType 类型
     * @return true
     */
    @Override
    public Boolean sendSms(String phone, Integer codeType) {
        if (stringRedisTemplate.hasKey(RedisUtils.send(phone, codeType))) {
            throw new UmsException(ResultEnum.VERIFICATION_CODES_ARE_SENT_FREQUENTLY);
        }
        String send = RedisUtils.send(phone, codeType);
        //生成位数验证码
        Integer code6 = SmsUtils.code(codeType);
        try {
            //    SmsUtils.x(phone, code6);
            stringRedisTemplate.opsForValue().set(send, String.valueOf(code6), 5, TimeUnit.MINUTES);
            log.info("验证码发送成功，手机号：{}，验证码：{}", phone, code6);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("验证码发送失败，手机号：{}，错误：{}", phone, e.getMessage());
            throw new UmsException(ResultEnum.FAILED_TO_SEND_VERIFICATION_CODE);
        }
    }
}
