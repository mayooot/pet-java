package com.hexu.petproject.service;

import com.hexu.petproject.model.pojo.Brand;

import java.util.List;

/**
 * @Classname BrandService
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-04 14:52
 * @Version 1.0
 **/
public interface BrandService {
    /**
     * 查询出所有品牌
     * @return
     */
    List<Brand> list();
}
