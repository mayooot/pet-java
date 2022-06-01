package com.hexu.petproject.service;

import com.hexu.petproject.model.vo.categoryVO.CategoryTreeVO;

import java.util.List;

/**
 * @Classname ProductCategoryService
 * @Description  分类接口
 * @Author 86176
 * @Date 2022-04-02 11:47
 * @Version 1.0
 **/
public interface ProductCategoryService {
    /**
     * 获取分类菜单
     * @return 树
     */
    List<CategoryTreeVO> listAll();

}
