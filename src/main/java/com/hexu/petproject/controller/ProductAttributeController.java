package com.hexu.petproject.controller;

import com.hexu.petproject.interceptor.Authentication;
import com.hexu.petproject.util.ResultVO;
import com.hexu.petproject.model.vo.attributeVO.AttributeTreeVO;
import com.hexu.petproject.service.ProductAttributeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname AttributeController
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-04 13:37
 * @Version 1.0
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/pms/product/attribute")
public class ProductAttributeController {

    @Resource
    private ProductAttributeService productAttributeService;
    @Authentication
    @PostMapping("/byCategoryIds")
    public ResultVO byCategoryIds(@RequestBody Integer[] ids){
         List<AttributeTreeVO> voList = productAttributeService.byCategoryIds(ids);
        return ResultVO.ok(voList);
    }
    @Authentication
    @GetMapping("/list")
    public ResultVO list(){
        List<AttributeTreeVO> voList = productAttributeService.list();
        return ResultVO.ok(voList);
    }
}
