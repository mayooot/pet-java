package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * <p>项目文档： TODO</p>
 * @author liming
 * @version 1.0.0
 * @createTime 2022年06月07日 23:50:00
 */

/**
 * 商品sku表
 */
@Data
public class MiaoshaSku implements Serializable {
    private Long id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 销量
     */
    private Integer sale;

    private Integer version;

    private static final long serialVersionUID = 1L;
}