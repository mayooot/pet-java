package com.hexu.petproject.controller;

import com.hexu.petproject.mapper.PayLogMapper;
import com.hexu.petproject.model.pojo.PayLog;
import com.hexu.petproject.util.RedisUtils;
import com.hexu.petproject.util.ResultEnum;
import com.hexu.petproject.util.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>项目文档： 使用雪花算法和redis实现幂等性</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年05月28日 21:23:00
 */
@Slf4j
@RestController
@RequestMapping("/api/idempotent")
public class IdempotentController {

    @Autowired
    private PayLogMapper payLogMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 支付接口幂等性实现
     * 1. 客户端先请求服务端，会拿到一个能代表这次请求业务的唯一字段
     * 2. 将该字段以SETNX的方式存入redis中，并根据业务设置相应的超时时间
     * 3. 如果设置成功，证明这是第一次请求，则执行后续的业务逻辑
     * 4. 如果设置失败，则代表已经执行过当前请求，直接返回
     *
     * @param id 订单id，雪花算法成功，可以保证唯一性
     */
    @PostMapping("/pay")
    public ResultVO pay(@RequestParam("id") String id) {

        // 存入redis前缀规范 pet:pay:id
        // id是使用雪花算法生成，可以代表这次请求业务的唯一字段
        String key = RedisUtils.payRedisKey(id);

        // 使用sessionCallback保证所有操作都在同一个Session中完成
        SessionCallback<Boolean> callback = new SessionCallback<Boolean>() {
            List exec = null;

            @Override
            public Boolean execute(RedisOperations operations) throws DataAccessException {
                // 开启事务
                operations.multi();
                // 使用SETNX的方式，将支付订单号存入redis中。设置失效时间为5分钟
                stringRedisTemplate.opsForValue().setIfAbsent(key, "succ", 5, TimeUnit.MINUTES);
                exec = operations.exec();
                if (exec.size() > 0) {
                    return (Boolean) exec.get(0);
                }
                return false;
            }
        };
        Boolean flag = stringRedisTemplate.execute(callback);

        if (!ObjectUtils.isEmpty(flag) && flag) {
            // 设置成功说明是第一次访问，执行业务逻辑
            PayLog payLog = new PayLog();
            payLog.setUserId(101L);
            payLog.setOrderId(Long.parseLong(id));
            payLog.setOrderNo("100000111");
            payLogMapper.insertSelective(payLog);
            log.warn("用户支付成功，支付金额为：897元");
            return ResultVO.ok();
        }

        return ResultVO.errer(ResultEnum.PAY_REPEAT);
    }
}
