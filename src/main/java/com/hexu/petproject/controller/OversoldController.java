package com.hexu.petproject.controller;

import com.hexu.petproject.exception.OmsException;
import com.hexu.petproject.mapper.SkuMapper;
import com.hexu.petproject.model.pojo.Sku;
import com.hexu.petproject.service.SkuService;
import com.hexu.petproject.util.RedisUtils;
import com.hexu.petproject.util.ResultEnum;
import com.hexu.petproject.util.ResultVO;
import com.hexu.petproject.util.redis.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>项目文档： 解决超卖问题</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年06月01日 11:45:00
 */
@Slf4j
@RestController
@RequestMapping("/api/product")
public class OversoldController {

    @Autowired
    private RedisLock redisLock;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SkuService skuService;

    /**
     * 使用redis分布式锁来解决多个同时抢购的问题
     * 使用SETNX将要抢购的商品ID存在redis里，如果设置成功代表抢到了分布式锁，失败则没有抢到
     * 删除锁的时候，因为我们设置的商品ID对应的value是线程ID+时间戳，所以使用lua脚本保证每个线程删除的都是自己的锁
     *
     * @param skuId
     * @param num
     * @return
     */
    @PostMapping("/oversold")
    public ResultVO oversold(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num) {

        // 生成存于redis中的key（商品id）
        String lockKey = RedisUtils.productRedisKey(String.valueOf(skuId));

        // 获取当前线程id
        Long threadId = Thread.currentThread().getId();
        // 获取当前时间戳
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        // 存入redis中的value，oversold:线程id:时间戳
        String lockValue = RedisUtils.productRedisValue(threadId, timestamp);

        // 获取锁
        Boolean lock = redisLock.lock(lockKey, lockValue);
        if (!lock) {
            // 获取锁失败
            return ResultVO.errer(ResultEnum.PRODUCT_TOO_MANY_BUYERS);
        }


        try {
            // 获取锁成功
            // 1. 查库存
            Sku sku = skuMapper.selectByPrimaryKey(skuId);
            if (sku.getStock() == 0 || num > sku.getStock()) {
                // 如果库存为0，或者购买数量大于库存
                throw new OmsException(ResultEnum.NOT_ENOUGH_STOCK);
            }

            // 2. 扣减库存
            skuService.deductedInventory(skuId, num);

            // 释放锁
            redisLock.unLockLua(lockKey, lockValue);
        } catch (OmsException e) {
            log.info("获取锁成功，但业务执行失败！失败原因：code:{}, cause: {}", e.getCode(), e.getCause());
        } finally {
            // 最后释放锁
            redisLock.unLockLua(lockKey, lockValue);
        }

        return ResultVO.ok();
    }
}
