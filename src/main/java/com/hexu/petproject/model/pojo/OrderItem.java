package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
    * 订单商品明细表
    */
@Data
public class OrderItem implements Serializable {

    private Long id;

    /**
    * 订单ID(对应订单表主键ID)
    */
    private Long orderId;

    /**
    * 商品spuID(对应商品spu表主键ID)
    */
    private Long spuId;

    /**
    * 商品图片
    */
    private String productPic;

    /**
    * 商品名称
    */
    private String productName;

    /**
    * 商品销售单价
    */
    private BigDecimal productPrice;

    /**
    * 购买数量
    */
    private Integer productQuantity;

    /**
    * 商品skuID(对应商品sku表主键ID)
    */
    private Long skuId;

    /**
    * 商品销售属性:[{"key":"颜色","value":"白色"},{"key":"容量","value":"4G"}]
    */
    private String productAttr;

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