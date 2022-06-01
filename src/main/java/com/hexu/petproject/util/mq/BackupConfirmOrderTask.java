package com.hexu.petproject.util.mq;

import com.hexu.petproject.config.ConfirmRabbitConfig;
import com.hexu.petproject.exception.OmsException;
import com.hexu.petproject.util.RedisService;
import com.hexu.petproject.util.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * <p>项目文档： 消费备份队列中的消息</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年05月26日 19:12:00
 */
@Slf4j
@Component
@RabbitListener(queues = {ConfirmRabbitConfig.CART_BACKUP_QUEUE_NAME})
public class BackupConfirmOrderTask {
    @Autowired
    private RedisService redisService;

    @RabbitHandler
    @Transactional(rollbackFor = {OmsException.class, RuntimeException.class})
    public void removeCart(ArrayList<Long> list) {
        Long userId = list.get(0);
        Long skuId = list.get(1);

        log.warn("发布确认清空购物车，用户id:{}, 商品id:{}", userId, skuId);

        int removeResult = redisService.removeProduct(userId, skuId);
        if (removeResult == 0) {
            log.error("清空购物车失败");
            throw new OmsException(ResultEnum.DELETE_CART_ERROR);
        }
    }
}
