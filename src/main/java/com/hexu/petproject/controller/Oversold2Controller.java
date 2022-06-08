package com.hexu.petproject.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.hexu.petproject.exception.OmsException;
import com.hexu.petproject.mapper.MiaoshaSkuMapper;
import com.hexu.petproject.model.pojo.MiaoshaSku;
import com.hexu.petproject.util.RedisUtils;
import com.hexu.petproject.util.ResultEnum;
import com.hexu.petproject.util.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * <p>项目文档： 解决超卖问题2 使用乐观锁+令牌桶+redis实现
 *  乐观锁解决多个用户同时抢购的问题
 *  令牌桶实现限流
 *  redis实现秒杀限时
 * </p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年06月01日 11:45:00
 */
@Slf4j
@RestController
@RequestMapping("/api/product")
public class Oversold2Controller {

    @Autowired
    private MiaoshaSkuMapper skuMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 每秒产生10个令牌
    private RateLimiter rateLimiter = RateLimiter.create(10);

    /**
     * 秒杀方法，每个用户只能抢购一件
     *
     * @param skuId
     * @return
     */
    @PostMapping("/oversold2")
    public ResultVO oversold(@RequestParam("skuId") Long skuId) {

        // 检测redis中秒杀的商品是否超时
        String redisKey = RedisUtils.miaoshaRedisKey(skuId);
        if (!stringRedisTemplate.hasKey(redisKey)) {
            // 如果不存在该键，说明该商品秒杀已经结束
            log.info("id为{}的商品秒杀活动已经过期！", skuId);
            return ResultVO.errer(ResultEnum.PRODUCT_KILL_HAS_ENDED);
        }
        // 加入令牌桶的限流措施
        if (!rateLimiter.tryAcquire(3, TimeUnit.SECONDS)) {
            // 如果3秒内，该请求还是没有获取到令牌，那么就抛弃
            log.info("抛弃请求：抢购失败，当前秒杀活动过于火爆，请重试");
            return ResultVO.errer(ResultEnum.PRODUCT_TOO_MANY_BUYERS);
        }

        // 获取到了令牌
        // 1. 查库存
        MiaoshaSku sku = skuMapper.selectByPrimaryKey(skuId);
        if (sku.getStock().equals(sku.getSale())) {
            // 如果该商品的库存和销量一样说明已经售罄
            throw new OmsException(ResultEnum.NOT_ENOUGH_STOCK);
        }

        // 2. 更新库存和版本号
        int records = skuMapper.updateStockAndVersionById(sku);
        if (records == 0) {
            // 说明没有更新成功，即秒杀失败
            return ResultVO.errer(ResultEnum.PRODUCT_TOO_MANY_BUYERS);
        }

        // 否则，更新成功
        return ResultVO.ok();
    }
}
