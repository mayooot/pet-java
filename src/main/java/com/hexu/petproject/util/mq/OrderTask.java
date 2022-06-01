package com.hexu.petproject.util.mq;

import com.hexu.petproject.common.SystemConstants;
import com.hexu.petproject.config.ConfirmRabbitConfig;
import com.hexu.petproject.config.DelayOrderRabbitConfig;
import com.hexu.petproject.mapper.OrderMapper;
import com.hexu.petproject.model.pojo.Order;
import com.hexu.petproject.service.OrderItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>项目文档： 订单消费接口</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年05月20日 19:22:00
 */
@Component
public class OrderTask {

    private static final Logger log = LoggerFactory.getLogger(OrderTask.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemService orderItemService;

    /**
     * 消费接口，监听死信队列
     * 接收到超时订单的ID，然后进行回滚操作
     *
     * @param message 订单ID,默认为byte[]类型
     */
    @RabbitListener(queues = {DelayOrderRabbitConfig.ORDER_QUEUE_DLX_NAME})
    public void consumer(Message message) {
        // byte[] --> String --> Long
        Long orderId = Long.parseLong(new String(message.getBody()));
        log.info("超时订单ID:{}", orderId);

        // 1. 修改当前订单状态
        Order order = orderMapper.selectByPrimaryKey(orderId);
        // 设置订单状态为已超时
        order.setOrderStatus(SystemConstants.Order.Status.TIMEOUT);

        // 2. 回滚对应商品的库存和销量
        orderItemService.rollBackTheInventory(order);
    }
}
