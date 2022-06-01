package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
    * 商品规格key表
    */
@Data
public class SpecParam implements Serializable {
    private Long id;

    /**
    * 商品分类ID(对应商品分类表主键ID)
    */
    private Long productCategoryId;

    /**
    * 规格key名称
    */
    private String name;

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