package com.hexu.petproject.model.vo;

import com.hexu.petproject.model.condition.SkuCondition;
import com.hexu.petproject.model.pojo.Order;
import lombok.Data;

import java.util.List;

/**
 * @Classname MqVO
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-19 12:07
 * @Version 1.0
 **/
@Data
public class MqVO {
    //订单
    private Order order;
    //商品库存
    private List<SkuCondition> skuConditionList;
}
