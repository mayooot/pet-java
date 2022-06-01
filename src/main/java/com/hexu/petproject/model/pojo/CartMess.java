package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * <p>项目文档： TODO</p>
 * @author liming
 * @version 1.0.0
 * @createTime 2022年05月31日 20:36:00
 */
@Data
public class CartMess implements Serializable {
    /**
    * 主键ID
    */
    private Long id;

    /**
    * 用户ID

    */
    private Long userid;

    /**
    * 商品ID

    */
    private Long skuid;

    /**
    * 消息状态，0=>已经发出但mq没有确认，1=>生产者已经收到确认
    */
    private Integer status;

    private static final long serialVersionUID = 1L;
}