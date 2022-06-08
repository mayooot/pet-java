package com.hexu.petproject.controller;

import com.hexu.petproject.util.RedisUtils;
import com.hexu.petproject.util.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * <p>项目文档： 添加秒杀商品到redis中</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年06月08日 13:42:00
 */
@RestController
@RequestMapping("/miaosha")
public class MiaoshaController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 添加秒杀商品到redis
     * @param skuId 商品id
     * @param expireTime 秒杀活动持续时间
     * @return
     */
    @PostMapping("/add")
    public ResultVO add(@RequestParam("skuId") Long skuId,
                        @RequestParam("expireTime") Long expireTime,
                        @RequestParam("type") Integer type) {

        // 设置时间单位，type为1单位为秒，type为2单位为分钟
        TimeUnit timeUnit = type == 1 ? TimeUnit.SECONDS : TimeUnit.MINUTES;
        // 生成redis中的key。 pet:miaosha:id
        String redisKey = RedisUtils.miaoshaRedisKey(skuId);

        stringRedisTemplate.opsForValue().set(redisKey, "OK", expireTime, timeUnit);
        return ResultVO.ok();
    }


}
