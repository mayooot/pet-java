package com.hexu.petproject.model.condition;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname 订单中的商品信息
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-12 14:13
 * @Version 1.0
 **/
@Data
public class SkuCondition implements Serializable {

    /**
     * 商品sku Id
     */
    private Long skuId;

    /**
     * 商品sku 数量
     */
    private Integer skuNum;
}
