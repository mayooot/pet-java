package com.hexu.petproject.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexu.petproject.exception.UmsException;
import com.hexu.petproject.model.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * <p>项目文档： Redis工具类</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年05月20日 16:13:00
 */
@Service
public class RedisService {

    private static Logger log = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 通过token获取用户信息
     */
    public User getUserByToken(String token) {

        if (!StringUtils.hasText(token)) {
            // 如果token为空，返回空对象
            return null;
        }

        // 获取该token在redis中对应的key
        String tokenRedisKey = RedisUtils.tokenRedisKey(token);

        // 从redis中获取用户信息
        String userString = stringRedisTemplate.opsForValue().get(tokenRedisKey);

        User user = null;
        try {
            user = objectMapper.readValue(userString, User.class);
        } catch (JsonProcessingException e) {
            log.error("用户信息反序列化失败");
            throw new UmsException(ResultEnum.COMMON_EXCEPTION);
        }

        return user;
    }

    /**
     * 删除用户购物车中的商品
     *
     * @param userId 用户ID
     * @param skuId  商品ID
     * @return 删除结果 0代表失败，1代表成功
     */
    public int removeProduct(Long userId, Long skuId) {
        // 使用自定义工具类RedisUtils获取该用户购物车信息在redis中的key
        String key = RedisUtils.cartRedisKey(userId.toString());
        try {
            HashOperations<String, String, String> hashOps = stringRedisTemplate.opsForHash();
            hashOps.delete(key, skuId.toString());
            log.info("[REDIS] Delete hash key={}, hashKey={}", key, skuId);
            return 1;
        } catch (Exception e) {
            log.error("[REDIS] Delete hash key={}, hashKey={}", key, skuId);
            return 0;
        }
    }
}
