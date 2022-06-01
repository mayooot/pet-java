package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
    * 用户收货地址表
    */
@Data
public class ReceiverAddress implements Serializable {
    private Long id;

    /**
    * 用户ID(对应用户表主键ID)
    */
    private Long userId;

    /**
    * 收件人名称
    */
    private String userName;

    /**
    * 收件人电话
    */
    private String phoneNumber;

    /**
    * 是否为默认收货地址：0->不是；1->是
    */
    private Integer defaultStatus;

    /**
    * 区县id
    */
    private Integer countyId;

    /**
    * 详细地址(街道)
    */
    private String detailAddress;

    /**
    * 创建时间
    */
    private LocalDateTime createdTime;

    /**
    * 修改时间
    */
    private LocalDateTime updatedTime;

    /**
    * 是否删除（0：否，1：是）
    */
    private Boolean isDelete;

    private static final long serialVersionUID = 1L;
}