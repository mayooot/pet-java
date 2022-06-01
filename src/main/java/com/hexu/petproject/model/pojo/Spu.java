package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
    * 商品spu表
    */
@Data
public class Spu implements Serializable {
    private Long id;

    /**
    * 品牌ID(对应品牌表主键ID)
    */
    private Long brandId;

    /**
    * 一级分类ID(对应商品分类表主键ID)
    */
    private Long cid1;

    /**
    * 二级分类ID(对应商品分类表主键ID)
    */
    private Long cid2;

    /**
    * 商品名称
    */
    private String name;

    /**
    * 副标题
    */
    private String subTitle;

    /**
    * 商品总销量
    */
    private Integer sale;

    /**
    * 评价总评分
    */
    private Integer commentTotalScore;

    /**
    * 商品评价数量
    */
    private Integer commentAmount;

    /**
    * 商品规格(json格式，用于商品详情页展示商品所有规格)
    */
    private String productSpecification;

    /**
    * 商品默认价格
    */
    private BigDecimal defaultPrice;

    /**
    * 商品默认图片URL
    */
    private String defaultPicUrl;

    /**
    * 上架状态：0->下架；1->上架
    */
    private Integer publishStatus;

    /**
    * 审核状态：0->未审核；1->审核通过
    */
    private Integer verifyStatus;

    /**
    * 是否有效，0->已删除；1->有效
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