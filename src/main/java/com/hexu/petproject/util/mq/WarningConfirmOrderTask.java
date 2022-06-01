package com.hexu.petproject.util.mq;

import com.hexu.petproject.config.ConfirmRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>项目文档： 消费报警队列中的消息并记录</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年05月26日 19:13:00
 */
@Slf4j
@Component
@RabbitListener(queues = {ConfirmRabbitConfig.CART_WARNING_QUEUE_NAME})
public class WarningConfirmOrderTask {

    public void receiveWarningMessage(Message message) {
        String msg = new String(message.getBody());
        log.error("报警！发现不可路由的消息：{}", msg);
    }
}
