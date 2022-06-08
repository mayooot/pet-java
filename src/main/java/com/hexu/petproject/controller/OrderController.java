package com.hexu.petproject.controller;

import com.hexu.petproject.interceptor.Authentication;
import com.hexu.petproject.model.condition.OrderSkuCondition;
import com.hexu.petproject.model.vo.orderVO.OrderDetailVO;
import com.hexu.petproject.model.vo.orderVO.OrderNoVO;
import com.hexu.petproject.model.vo.orderVO.OrderVO;
import com.hexu.petproject.service.OrderService;
import com.hexu.petproject.util.RedisUtils;
import com.hexu.petproject.util.ResultEnum;
import com.hexu.petproject.util.ResultVO;
import com.hexu.petproject.util.SmsUtils;
import com.hexu.petproject.util.redis.RedisLock;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

    @Autowired
    private RedisLock redisLock;

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
     * 将用户token和购物车信息放进购物车中，通过SETNX来防止重复提交
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

        // 订单信息加入Redis
        Boolean flag = redisLock.lock("pet:order:" + token + orderConditions.toString(), "OK");

        if (flag) {
            // 获取锁成功，说明该订单是第一次提交，可以进行提交业务
            // 调用OrderService新增订单
            OrderNoVO add = orderService.add(orderConditions, token);
            return ResultVO.ok(add);
        } else {
            // 该订单已经提交过了
            return ResultVO.errer(ResultEnum.REPEAT_ORDER);
        }

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
