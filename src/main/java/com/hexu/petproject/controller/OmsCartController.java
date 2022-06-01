package com.hexu.petproject.controller;

import com.hexu.petproject.service.CartService;
import com.hexu.petproject.util.ResultVO;
import com.hexu.petproject.util.SmsUtils;
import org.apache.ibatis.util.MapUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @Classname OmsController
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-09 14:04
 * @Version 1.0
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/oms/cart")
public class OmsCartController {
    @Resource
    private CartService cartService;

    @PostMapping("/add")
    public ResultVO add(Long skuId, Integer num, HttpServletRequest request) {
        String token = SmsUtils.token(request);
        cartService.insertCart(skuId, num, token);
        return ResultVO.ok();
    }

    @PostMapping("/remove")
    public ResultVO remove(String skuIds, HttpServletRequest request) {
        String token = SmsUtils.token(request);
        cartService.remove(skuIds, token);
        return ResultVO.ok();
    }

    @GetMapping("/cartList")
    public ResultVO cartList(HttpServletRequest request) {
        String token = SmsUtils.token(request);
        List<Object> list = cartService.cartList(token);
        return ResultVO.ok(list);
    }

    @GetMapping("/cartCount")
    public ResultVO cartCount(HttpServletRequest request) {
        String token = SmsUtils.token(request);
        Long count = cartService.cartCount(token);

        HashMap<String, Long> map = new HashMap<>();
        map.put("cartCount", count);
        return ResultVO.ok(map);
    }

}
