package com.hexu.petproject.controller;

import com.hexu.petproject.interceptor.Authentication;
import com.hexu.petproject.model.condition.OrderSkuCondition;
import com.hexu.petproject.model.vo.orderVO.OrderDetailVO;
import com.hexu.petproject.model.vo.orderVO.OrderNoVO;
import com.hexu.petproject.model.vo.orderVO.OrderVO;
import com.hexu.petproject.service.OrderService;
import com.hexu.petproject.util.ResultEnum;
import com.hexu.petproject.util.ResultVO;
import com.hexu.petproject.util.SmsUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Classname 订单功能Controller
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-12 14:07
 * @Version 1.0
 **/
@CrossOrigin
@Controller
@RequestMapping("/api/oms/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 生成预提交订单
     *
     * @param orderConditions
     * @return
     */
    @PostMapping("/preSubmit")
    @ResponseBody
    public ResultVO prepayment(@RequestBody OrderSkuCondition orderConditions) {
        OrderVO list = orderService.preSubmit(orderConditions);
        return ResultVO.ok(list);
    }

    /**
     * 新增订单
     */
    @PostMapping("/add")
    @ResponseBody
    public ResultVO add(@RequestBody OrderSkuCondition orderConditions, HttpServletRequest request) {

        // 获取token
        String token = request.getHeader("token");

        if (!StringUtils.hasText(token)) {
            // 如果token为空
            return ResultVO.errer(ResultEnum.AUTH_TOKEN_EMPTY);
        }

        // 调用OrderService新增订单
        OrderNoVO add = orderService.add(orderConditions, token);

        return ResultVO.ok(add);
    }

    @GetMapping("/detail/{orderNo}")
    @ResponseBody
    public ResultVO detail(@PathVariable String orderNo, HttpServletRequest request) {
        String token = SmsUtils.token(request);
        OrderDetailVO orderDetailVO = orderService.detail(orderNo, token);
        return ResultVO.ok(orderDetailVO);
    }

    @PostMapping("/notify")
    @ResponseBody
    public ResultVO notify(HttpServletRequest request) {
        System.out.println("支付宝页面跳转同步通知页面");
        return ResultVO.ok();
    }

    @GetMapping("/return")
    @Authentication
    public String return1() {
        System.out.println("支付成功");
        return "redirect:http://localhost:8080/";
    }
}
