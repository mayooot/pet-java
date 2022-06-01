package com.hexu.petproject.controller;

import com.hexu.petproject.model.condition.AddressCondition;
import com.hexu.petproject.model.vo.pcdDicVO.DicVO;
import com.hexu.petproject.model.vo.receiverAddressVO.ListVO;
import com.hexu.petproject.service.UmsRceiverAddressService;
import com.hexu.petproject.util.ResultVO;
import com.hexu.petproject.util.SmsUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Classname UmsRceiverAddress
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-09 15:56
 * @Version 1.0
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/ums/receiverAddress")
public class UmsRceiverAddressController {

    @Resource
    private UmsRceiverAddressService umsRceiverAddressService;

    @GetMapping("/pcdList")
    public ResultVO pcdList() {
        List<DicVO> dicVOS = umsRceiverAddressService.dicList();
        return ResultVO.ok(dicVOS);
    }

    @PostMapping("/saveOrUpdate")
    public ResultVO saveOrUpdate(AddressCondition addressCondition, HttpServletRequest request){
        System.out.println(" 55555555"+addressCondition);
        String token = SmsUtils.token(request);
        addressCondition.setToken(token);
        umsRceiverAddressService.saveOrUpdate(addressCondition);
        return ResultVO.ok();
    }

    @GetMapping("/list")
    public ResultVO list(Integer userId, HttpServletRequest request){
        String token = SmsUtils.token(request);
        List<ListVO> list = umsRceiverAddressService.list(userId,token);
        return ResultVO.ok(list);
    }

    @PostMapping("/remove")
    public ResultVO remove(Long addressId, HttpServletRequest request){
        String token = SmsUtils.token(request);
        Boolean bo= umsRceiverAddressService.remove(addressId,token);
        return ResultVO.ok();
    }
}
