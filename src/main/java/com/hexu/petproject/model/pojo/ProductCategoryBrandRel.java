package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
    * 商品分类和品牌关联表
    */
@Data
public class ProductCategoryBrandRel implements Serializable {
    private Long id;

    /**
    * 商品分类ID(对应商品分类表主键ID)
    */
    private Long productCategoryId;

    /**
    * 商品品牌ID(对应商品品牌表主键ID)
    */
    private Long brandId;

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