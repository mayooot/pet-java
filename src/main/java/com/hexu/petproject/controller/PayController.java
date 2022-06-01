package com.hexu.petproject.controller;

import com.alipay.api.AlipayApiException;
import com.hexu.petproject.interceptor.Authentication;
import com.hexu.petproject.service.PayService;
import com.hexu.petproject.util.SmsUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Classname PayController
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-22 12:12
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/oms/pay/aliPay")
public class PayController {

    @Resource
    private PayService payService;

    @GetMapping("/pay")
    @Authentication
    public String pay(HttpServletRequest request, String orderNo) throws AlipayApiException {
        String token = SmsUtils.token(request);

        String pay = payService.pay(orderNo, token);
        System.out.println("66661111" + pay);

        return pay;
    }
}
