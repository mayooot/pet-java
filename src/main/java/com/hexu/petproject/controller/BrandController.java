package com.hexu.petproject.controller;

import com.hexu.petproject.interceptor.Authentication;
import com.hexu.petproject.model.pojo.Brand;
import com.hexu.petproject.util.ResultVO;
import com.hexu.petproject.service.BrandService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname BrandController
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-04 14:51
 * @Version 1.0
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/pms/product/brand")
public class BrandController {

    @Resource
    private BrandService brandService;

    @Authentication
    @GetMapping("/list")
    public ResultVO list() {
        List<Brand> list = brandService.list();
        return ResultVO.ok(list);
    }
}
