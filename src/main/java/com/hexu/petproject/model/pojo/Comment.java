package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
    * 商品评价表
    */
@Data
public class Comment implements Serializable {
    private Long id;

    /**
    * 商品spuID(对应商品spu表主键ID)
    */
    private Long spuId;

    /**
    * 用户ID(对应用户表主键ID)
    */
    private Long userId;

    /**
    * 评价星数：0->5
    */
    private Integer star;

    /**
    * 评价内容
    */
    private String content;

    /**
    * 评价图片：0->无图；1->有图
    */
    private Integer isPic;

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