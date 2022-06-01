package com.hexu.petproject.controller;
import com.hexu.petproject.interceptor.Authentication;
import com.hexu.petproject.model.vo.categoryVO.CategoryTreeVO;
import com.hexu.petproject.util.ResultVO;
import com.hexu.petproject.service.ProductCategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname CategoryTreeController
 * @Description  分类
 * @Author 86176
 * @Date 2022-04-02 11:31
 * @Version 1.0
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/pms/product/category")
public class ProductCategoryController {

    @Resource
    private ProductCategoryService productCategoryService;

    @Authentication
    @GetMapping("list")
    public ResultVO list() {
        final List<CategoryTreeVO> categoryTreeVOS = productCategoryService.listAll();
        return ResultVO.ok(categoryTreeVOS);
    }
}
