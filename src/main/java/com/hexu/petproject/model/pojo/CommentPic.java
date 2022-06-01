package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
    * 商品评价图片表
    */
@Data
public class CommentPic implements Serializable {
    private Long id;

    /**
    * 评价ID(对应商品评价表主键ID)
    */
    private Long commentId;

    /**
    * 图片URL
    */
    private String picUrl;

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