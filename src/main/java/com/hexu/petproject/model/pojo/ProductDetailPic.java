package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
    * 商品详情图片表
    */
@Data
public class ProductDetailPic implements Serializable {
    private Long id;

    /**
    * 商品spuID(对应商品spu表主键ID)
    */
    private Long spuId;

    /**
    * 图片URL
    */
    private String picUrl;

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