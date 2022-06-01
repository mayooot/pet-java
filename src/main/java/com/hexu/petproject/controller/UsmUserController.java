package com.hexu.petproject.controller;

import com.hexu.petproject.interceptor.Authentication;
import com.hexu.petproject.model.vo.userVO.UserVO;
import com.hexu.petproject.service.UserService;
import com.hexu.petproject.util.ResultVO;
import com.hexu.petproject.util.ResultEnum;
import com.hexu.petproject.util.SmsUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname UsmUserController
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-06 15:15
 * @Version 1.0
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/ums/user")
public class UsmUserController {

    @Resource
    private UserService userService;

    @Authentication
    @PostMapping("/login")
    public ResultVO login(String phone, String sms) {
        //验证手机号格式
        if (!SmsUtils.checkPhone(phone)) {
            return ResultVO.errer(ResultEnum.MALFORMED_PHONE_NUMBER);
        }
        //获取用户redis的UUID
        String bool = userService.clickSend(phone, sms);
        //存进请求头
        Map<String, String> hashMap = new HashMap<>(1);
        hashMap.put("token", bool);
        return ResultVO.ok(hashMap);
    }

    @Authentication
    @GetMapping("/getUserByToken")
    public ResultVO getUserByToken(HttpServletRequest request) {
        //获取请求头的UUID
        String token = SmsUtils.token(request);
        UserVO userVo = userService.getUUID(token);
        return ResultVO.ok(userVo);
    }

    @GetMapping("/logout")
    public ResultVO logout(HttpServletResponse response) {
        return ResultVO.ok();
    }
}
