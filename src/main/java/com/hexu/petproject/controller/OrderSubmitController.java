package com.hexu.petproject.controller;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * <p>项目文档： 防止用户重复提交订单</p>
 * 使用令牌桶实习限流
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年06月07日 17:32:00
 */
@RestController
@RequestMapping("/orderSubmit")
@Slf4j
public class OrderSubmitController {

    // 创建令牌桶实例 参数代表令牌桶只放行10个实例
    private RateLimiter rateLimiter = RateLimiter.create(10);


    @GetMapping("/sale")
    public String sale(Integer id) {
        // 1. 没有获取到token，请求一直等待直到获取到token令牌
        // log.info("等待的时间:{}", rateLimiter.acquire());

        // 2. 设置一个等待时间，如果在等待的时间内获取到了token令牌，则处理业务，如果在等待时间内没有获取到token则抛弃
        if (!rateLimiter.tryAcquire(5, TimeUnit.SECONDS)) {
            // 如果5秒内没有获取到token令牌
            log.info("秒杀失败....");
            return " 秒杀失败！";
        } else {
            log.info("处理业务逻辑....");
            return "秒杀成功！";
        }
    }
}
