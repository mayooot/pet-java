package com.hexu.petproject.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * <p>项目文档： 发布确认模式实现用户下单后清空购物车</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年05月26日 16:23:00
 */
@Configuration
public class ConfirmRabbitConfig {
    // 声明确认交换机的名称
    public static final String CART_CONFIRM_EXCHANGE_NAME = "pet_cart_confirm_exchange";
    // 声明确认队列的名称
    public static final String CART_CONFIRM_QUEUE_NAME = "pet_cart_confirm_queue";
    // 声明确认交换机路由到确认队列的RoutingKey
    public static final String CART_CONFIRM_ROUTING_KEY = "pet_cart_confirm_key";

    // 声明备份交换机的名称
    public static final String CART_BACKUP_EXCHANGE_NAME = "pet_cart_confirm_backup_exchange";
    // 声明备份队列的名称
    public static final String CART_BACKUP_QUEUE_NAME = "pet_cart_confirm_backup_queue";
    // 声明报警队列的名称
    public static final String CART_WARNING_QUEUE_NAME = "pet_cart_confirm_warning_queue";


    /**
     * 声明确认交换机
     *
     * @return
     */
    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        return ExchangeBuilder.directExchange(CART_CONFIRM_EXCHANGE_NAME)
                .durable(true)
                // 指定备份交换机（发生故障后，确认交换机路由到备份交换机）
                .withArgument("alternate-exchange", CART_BACKUP_EXCHANGE_NAME)
                .build();
    }

    /**
     * 声明确认队列
     *
     * @return
     */
    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(CART_CONFIRM_QUEUE_NAME).build();
    }

    /**
     * 绑定确认交换机和确认队列
     *
     * @param confirmExchange
     * @param confirmQueue
     * @return
     */
    @Bean
    public Binding queueBindingExchange(@Qualifier("confirmExchange") DirectExchange confirmExchange,
                                        @Qualifier("confirmQueue") Queue confirmQueue) {
        return BindingBuilder.bind(confirmQueue)
                .to(confirmExchange)
                .with(CART_CONFIRM_ROUTING_KEY);
    }

    /**
     * 声明备份交换机，类型为扇出
     *
     * @return
     */
    @Bean("backupExchange")
    public FanoutExchange backupExchange() {
        return new FanoutExchange(CART_BACKUP_EXCHANGE_NAME);
    }

    /**
     * 声明备份队列
     *
     * @return
     */
    @Bean("backupQueue")
    public Queue backupQueue() {
        return QueueBuilder.durable(CART_BACKUP_QUEUE_NAME).build();
    }

    /**
     * 声明报警队列
     *
     * @return
     */
    @Bean("warningQueue")
    public Queue warningQueue() {
        return QueueBuilder.durable(CART_WARNING_QUEUE_NAME).build();
    }

    /**
     * 绑定备份队列和备份交换机
     *
     * @param backupExchange
     * @param backupQueue
     * @return
     */
    @Bean
    public Binding backupQueueBindingBackupExchange(@Qualifier("backupExchange") FanoutExchange backupExchange,
                                                    @Qualifier("backupQueue") Queue backupQueue) {
        return BindingBuilder.bind(backupQueue).to(backupExchange);
    }

    /**
     * 绑定报警队列和备份交换机
     *
     * @param backupExchange
     * @param warningQueue
     * @return
     */
    @Bean
    public Binding warningQueueBindingBackupExchange(@Qualifier("backupExchange") FanoutExchange backupExchange,
                                                     @Qualifier("warningQueue") Queue warningQueue) {
        return BindingBuilder.bind(warningQueue).to(backupExchange);
    }
}
