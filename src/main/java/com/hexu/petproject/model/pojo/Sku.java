package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
    * 商品sku表
    */
@Data
public class Sku implements Serializable {
    private Long id;

    /**
    * 商品spuID(对应商品spu表主键ID)
    */
    private Long spuId;

    /**
    * 商品标题
    */
    private String title;

    /**
    * 价格
    */
    private BigDecimal price;

    /**
    * 商品单位
    */
    private String unit;

    /**
    * 库存
    */
    private Integer stock;

    /**
    * 销量
    */
    private Integer sale;

    /**
    * spu中商品规格的对应下标组合
    */
    private String indexes;

    /**
    * 商品sku规格(json格式，反序列化时请使用linkedHashMap，保证有序)
    */
    private String productSkuSpecification;

    /**
    * 默认规格：0->不是；1->是
    */
    private Integer isDefault;

    /**
    * 是否有效，0->无效；1->有效
    */
    private Boolean valid;

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