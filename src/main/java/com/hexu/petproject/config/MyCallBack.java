package com.hexu.petproject.config;

import com.hexu.petproject.mapper.CartMessMapper;
import com.hexu.petproject.model.pojo.CartMess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * <p>项目文档： 发布确认回调</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年05月26日 18:05:00
 */
@Slf4j
@Component
public class MyCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private CartMessMapper cartMessMapper;

    /**
     * 因为ConfirmCallback为RabbitTemplate的内部接口，
     * 所以要将自定义的回调函数MyCallBack注入进RabbitTemplate
     * 执行顺序（先---->后）：
     * Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)
     */
    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    /**
     * 交换机确认回调方法
     * @param correlationData 保存回调消息的ID及相关信息
     * @param ack 交换机是否收到消息
     * @param cause 失败的原因，成功接收则为NULL
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        String id = correlationData != null ? correlationData.getId() : "";
        if (ack) {
            log.info("交换机已经收到ID为{}的消息", id);
            CartMess mess = new CartMess();
            mess.setId(Long.valueOf(id));
            mess.setStatus(1);
            cartMessMapper.updateByPrimaryKeySelective(mess);
        } else {
            log.info("交换机未收到ID为{}的消息，原因为：{}", id, cause);
        }
    }

    /**
     * 当消息传递过程中不可达目的地时，将消息会退给生产者
     * @param returned 回退消息对象
     */
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("消息：{}，被交换机{}退回，退回原因：{}，路由Key：{}",
                new String(returned.getMessage().getBody()),
                returned.getExchange(),
                returned.getReplyText(),
                returned.getRoutingKey());
    }

}
