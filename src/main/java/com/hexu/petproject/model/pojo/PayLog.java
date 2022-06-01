package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
    * 支付记录表
    */
@Data
public class PayLog implements Serializable {
    private Long id;

    /**
    * 用户ID(对应用户表主键ID)
    */
    private Long userId;

    /**
    * 订单ID(对应订单表主键ID)
    */
    private Long orderId;

    /**
    * 订单编号
    */
    private String orderNo;

    /**
    * 支付方式：1->支付宝支付；2->微信支付
    */
    private Integer payWay;

    /**
    * 第三方支付订单交易号
    */
    private String payTradeNo;

    /**
    * 支付状态：1->支付成功；2->支付失败
    */
    private Integer payStatus;

    /**
    * 支付金额
    */
    private BigDecimal payAmount;

    /**
    * 创建时间
    */
    private LocalDateTime createdTime;

    /**
    * 修改时间
    */
    private LocalDateTime updatedTime;

    private static final long serialVersionUID = 1L;
}