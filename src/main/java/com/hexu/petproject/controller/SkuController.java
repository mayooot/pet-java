package com.hexu.petproject.controller;

import com.hexu.petproject.common.Page;
import com.hexu.petproject.interceptor.Authentication;
import com.hexu.petproject.model.condition.ProductSearchCondition;
import com.hexu.petproject.model.vo.skuVO.SkuDetail;
import com.hexu.petproject.util.ResultVO;
import com.hexu.petproject.model.vo.skuVO.ProductVO;
import com.hexu.petproject.service.SkuService;
import com.hexu.petproject.util.ResultEnum;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname ProductController
 * @Description  商品搜索
 * @Author 86176
 * @Date 2022-04-01 15:29
 * @Version 1.0
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/pms/product")
public class SkuController {
    @Resource
    private SkuService skuService;
    @Authentication
    @PostMapping("/search")
    private ResultVO search(@RequestBody ProductSearchCondition parameter){
         Page<List<ProductVO>> page = skuService.page(parameter);
         if (StringUtils.isEmpty(page)){
             return ResultVO.errer(ResultEnum.PRODUCT_SEARCH_ERROR);
         }
        return ResultVO.ok(page);
    }
    @Authentication
    @GetMapping("/detail")
    public ResultVO detail(Long spuId){
        SkuDetail skuDetail = skuService.skuDetailOne(spuId);
        return ResultVO.ok(skuDetail);
    }


}
