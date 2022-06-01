package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

/**
    * 用户表
    */
@Data
public class User implements Serializable {
    private Long id;

    /**
    * 账号
    */
    private String account;

    /**
    * 微信openId
    */
    private String openid;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 用户头像URL
    */
    private String headUrl;

    /**
    * 性别 0->保密，1->男性，2->女性
    */
    private Integer sex;

    /**
    * 密码
    */
    private String password;

    /**
    * 创建时间
    */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdTime;

    /**
    * 修改时间
    */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedTime;

    private static final long serialVersionUID = 1L;
}