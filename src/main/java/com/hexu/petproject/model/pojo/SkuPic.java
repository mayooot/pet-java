package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
    * 商品sku图片表
    */
@Data
public class SkuPic implements Serializable {
    private Long id;

    /**
    * 商品skuID(对应商品sku表主键ID)
    */
    private Long skuId;

    /**
    * 图片URL
    */
    private String picUrl;

    /**
    * 默认展示：0->否；1->是
    */
    private Integer isDefault;

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