package com.hexu.petproject.service;

import com.hexu.petproject.model.condition.OrderSkuCondition;
import com.hexu.petproject.model.pojo.Order;
import com.hexu.petproject.model.pojo.OrderItem;
import com.hexu.petproject.model.vo.cartVO.CartVo;

/**
 * @Classname OrderItemService
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-14 11:30
 * @Version 1.0
 **/
public interface OrderItemService {
    /**
     * 增加商品明细
     * @param order 订单
     * @param orderConditions
     * @return
     */
    int insertOrderItem(Order order, OrderSkuCondition orderConditions,String token);

    /**
     * 获取到所有订单下的订单信息并回滚商品库存
     * @param order
     */
    void rollBackTheInventory(Order order);

    /**
     * 新增订单明细
     * @param orderItem
     * @return
     */
    int saveOrderItem(OrderItem orderItem);
}
