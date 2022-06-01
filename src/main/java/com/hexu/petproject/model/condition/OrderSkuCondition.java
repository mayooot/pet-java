package com.hexu.petproject.model.condition;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname 订单商品对象
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-12 14:13
 * @Version 1.0
 **/
@Data
public class OrderSkuCondition implements Serializable {

   /**
    * 订单对应的商品规格及对应的数据
    */
   private List<SkuCondition> skuInfos;


   /**
    * 收货人姓名
    */
   private String receiverName;

   /**
    * 收货人手机号
    */
   private String receiverPhone;

   /**
    * 收货地址
    */
   private String receiverAddress;


}
