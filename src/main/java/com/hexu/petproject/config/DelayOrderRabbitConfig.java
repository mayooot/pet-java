package com.hexu.petproject.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>项目文档：订单延时队列配置（TTL+DLX实现） </p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年05月20日 17:47:00
 */
@Configuration
public class DelayOrderRabbitConfig {

    /*正常订单交换机、队列、路由*/
    public static final String ORDER_EXCHANGE_NAME = "pet_order_exchange";
    public static final String ORDER_QUEUE_NAME = "pet_order_queue";
    public static final String ORDER_ROUTING_KEY = "pet_order_routing_key";

    /*超时订单交换机、队列、路由*/
    public static final String ORDER_EXCHANGE_DLX_NAME = "pet_order_dlx_exchange";
    public static final String ORDER_QUEUE_DLX_NAME = "pet_order_dlx_queue";
    public static final String ORDER_ROUTING_DLX_KEY = "pet_order_dlx_routing_key";


    @Bean
    public Exchange orderExchange() {
        return ExchangeBuilder.directExchange(ORDER_EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean
    public Queue orderQueue() {
        return QueueBuilder.durable(ORDER_QUEUE_NAME)
                // 指定死信队列相关信息
                .withArgument("x-dead-letter-exchange", ORDER_EXCHANGE_DLX_NAME)
                .withArgument("x-dead-letter-routing-key", ORDER_ROUTING_DLX_KEY)
                .build();
    }

    @Bean
    public Binding orderBinding(Exchange orderExchange, Queue orderQueue) {
        return BindingBuilder.bind(orderQueue)
                .to(orderExchange)
                .with(ORDER_ROUTING_KEY)
                .noargs();
    }


    // 死信队列相关配置
    @Bean
    public Exchange orderDlxExchange() {
        return ExchangeBuilder.directExchange(ORDER_EXCHANGE_DLX_NAME)
                .durable(true)
                .build();
    }


    @Bean
    public Queue orderDlxQueue() {
        return QueueBuilder.durable(ORDER_QUEUE_DLX_NAME).build();
    }

    @Bean
    public Binding orderDlxBinding(Exchange orderDlxExchange, Queue orderDlxQueue) {
        return BindingBuilder.bind(orderDlxQueue)
                .to(orderDlxExchange)
                .with(ORDER_ROUTING_DLX_KEY)
                .noargs();
    }
}
