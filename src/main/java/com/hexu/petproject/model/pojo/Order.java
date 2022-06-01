package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 订单表
 */
@Data
public class Order implements Serializable {

    private Long id;

    /**
     * 用户ID(对应用户表主键ID)
     */
    private Long userId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单总金额合计
     */
    private BigDecimal totalMoney;

    /**
     * 实付金额合计
     */
    private BigDecimal payMoney;

    /**
     * 数量合计
     */
    private Integer totalNum;

    /**
     * 支付方式：0->在线支付；1->货到付款
     */
    private Integer payType;

    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->超时
     */
    private Integer orderStatus;

    /**
     * 支付状态：0->未支付；1->支付成功；2->支付失败
     */
    private Integer payStatus;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 收件人姓名
     */
    private String receiverName;

    /**
     * 收货人手机号
     */
    private String receiverPhone;

    /**
     * 收件人地址
     */
    private String receiverAddress;

    /**
     * 订单过期时间
     */
    private LocalDateTime expirationTime;

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