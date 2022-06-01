package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
    * 商品分类表
    */
@Data
public class ProductCategory implements Serializable {
    private Long id;

    /**
    * 上级分类的编号：0->一级分类
    */
    private Long parentId;

    /**
    * 分类名称
    */
    private String name;

    /**
    * 分类名称简称
    */
    private String abbreviation;

    /**
    * 分类级别：0->1级；1->2级
    */
    private Integer level;

    /**
    * 显示状态：0->不显示；1->显示
    */
    private Integer showStatus;

    /**
    * 图标
    */
    private String icon;

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