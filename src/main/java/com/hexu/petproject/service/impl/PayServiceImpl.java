package com.hexu.petproject.service.impl;

import com.alipay.api.AlipayApiException;
import com.hexu.petproject.config.AlipayBean;
import com.hexu.petproject.mapper.OrderMapper;
import com.hexu.petproject.model.pojo.Order;
import com.hexu.petproject.service.PayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Classname PayServiceImpl
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-22 12:20
 * @Version 1.0
 **/
@Service
public class PayServiceImpl implements PayService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private AlipayService alipayService;

    @Override
    public String pay(String orderNo, String token) throws AlipayApiException {
        Order detail = orderMapper.detail(orderNo);
        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setBody("666");
        alipayBean.setSubject("666");
        alipayBean.setTotal_amount("666");
        alipayBean.setOut_trade_no(orderNo);
        return alipayService.pay(alipayBean);
    }
}
