package com.hexu.petproject.util.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>项目文档： redis实现分布式锁解决超卖问题</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年06月01日 10:25:00
 */
@Slf4j
@Component
public class RedisLock {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 解锁脚本，一定要确认锁的value，防止误解锁
    private static final String script_unlock = "if redis.call('get', KEYS[1]) == ARGV[1] then\n" +
            "    return redis.call('del', KEYS[1])\n" +
            "else\n" +
            "    return 0\n" +
            "end\n";

    /**
     * 获取分布式锁
     *
     */
    public Boolean lock(String lockKey, String lockValue) {


        SessionCallback<Boolean> callback = new SessionCallback<Boolean>() {
            List exec = null;

            @Override
            public Boolean execute(RedisOperations operations) throws DataAccessException {
                // 开启事务
                operations.multi();
                // 使用SETNX的方式，将支付订单号存入redis中。设置失效时间为30s
                stringRedisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 30, TimeUnit.SECONDS);
                exec = operations.exec();
                if (exec.size() > 0) {
                    return (Boolean) exec.get(0);
                }
                return false;
            }
        };
        return stringRedisTemplate.execute(callback);
    }

    /**
     * 删除分布式锁
     * @param lockKey
     * @param lockValue
     */
    public void unLockLua(String lockKey, String lockValue) {
        // 默认为0
        Long result = 0L;
        try {
            //调用lua脚本并执行
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            // 设置返回类型是Long
            redisScript.setResultType(Long.class);
            //lua文件存放在resources目录下的redis文件夹内
            redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/redis_lock.lua")));
            // 如果删除锁成功，result为1
            result = stringRedisTemplate.execute(redisScript, Collections.singletonList(lockKey), lockValue);
        } catch (Exception e) {
            log.error("删除redis分布式锁失败！");
        }
        ObjectUtils.isEmpty(result);
    }
}
