package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
    * 商品属性表
    */
@Data
public class ProductAttribute implements Serializable {
    private Long id;

    /**
    * 类型：0->属性key；1->属性value
    */
    private Integer type;

    /**
    * "属性key"ID：0->属性key
    */
    private Long parentId;

    /**
    * 属性名称
    */
    private String name;

    /**
    * 排序
    */
    private Integer sort;

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