package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
    * 品牌表
    */
@Data
public class Brand implements Serializable {
    private Long id;

    /**
    * 品牌名称
    */
    private String name;

    /**
    * 品牌的首字母
    */
    private String firstLetter;

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