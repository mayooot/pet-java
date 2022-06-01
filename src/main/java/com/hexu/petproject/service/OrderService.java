package com.hexu.petproject.service;

import com.hexu.petproject.model.condition.OrderSkuCondition;
import com.hexu.petproject.model.vo.orderVO.OrderDetailVO;
import com.hexu.petproject.model.vo.orderVO.OrderNoVO;
import com.hexu.petproject.model.vo.orderVO.OrderVO;

import java.util.List;

/**
 * @Classname OrderService
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-12 15:03
 * @Version 1.0
 **/
public interface OrderService {
    /**
     * 查询订单预支付信息接口
     * @param orderConditions
     * @return
     */
    OrderVO preSubmit(OrderSkuCondition orderConditions);

    /**
     * 增加订单
     * @param orderConditions
     */
    OrderNoVO add(OrderSkuCondition orderConditions, String token);

    /**
     * 查询订单详情接口
     * @param orderNo 订单id
     * @param token 用户信息
     * @return
     */
    OrderDetailVO detail(String orderNo, String token);
}
