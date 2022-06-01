package com.hexu.petproject.service.impl;

import com.hexu.petproject.exception.UmsException;
import com.hexu.petproject.mapper.UserMapper;
import com.hexu.petproject.model.pojo.User;
import com.hexu.petproject.model.vo.userVO.UserVO;
import com.hexu.petproject.service.UserService;
import com.hexu.petproject.util.RedisUtils;
import com.hexu.petproject.util.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Classname UserService
 * @Description 短信验证
 * @Author 86176
 * @Date 2022-04-06 15:27
 * @Version 1.0
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 判断是否注册过  否就注册  true 登录
     *
     * @param phone 手机号
     * @param sms   验证码
     * @return
     */
    @Override
    public String clickSend(String phone, String sms) {
        //手机号格式获取
        String send = RedisUtils.send(phone, 1);
        //验证码
        String yzm = stringRedisTemplate.opsForValue().get(send);
        if (yzm != null && !yzm.equals(sms)) {
            log.error("验证码不正确");
            throw new UmsException(ResultEnum.VERIFICATION_CODE_VERIFICATION_FAILED);
        }
        User user = userMapper.getByPhoneOne(phone);

        //不存在就进行注册
        if (user == null) {
            user = new User();
            user.setAccount(phone);
            user.setUserName(phone);
            userMapper.insertSelective(user);
            log.info("注册用户成功");
        }
        //把登录用户保存在redis中
        String uuid = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(RedisUtils.send(uuid), user);
        log.info("用户执行登录------");
        return uuid;
    }

    /**
     * 根据请求头的uuid获取redis的用户信息
     *
     * @param token uuid
     * @return
     */
    @Override
    public UserVO getUUID(String token) {
        //根据请求头的token获取当前用户
        User o = (User) redisTemplate.opsForValue().get(RedisUtils.send(token));
        if (o != null) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(o, userVO);
            return userVO;
        }
        return null;
    }
}
