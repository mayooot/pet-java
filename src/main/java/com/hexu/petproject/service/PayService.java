package com.hexu.petproject.service;

import com.alipay.api.AlipayApiException;

/**
 * @Classname PayService
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-22 12:20
 * @Version 1.0
 **/
public interface PayService {
    /**
     *
     * @param orderNo 订单流水号
     * @param token 用户信息
     * @return
     * @throws AlipayApiException
     */
    String pay(String orderNo, String token) throws AlipayApiException;
}
