package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.ProductCategoryBrandRel;

public interface ProductCategoryBrandRelMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ProductCategoryBrandRel record);

    ProductCategoryBrandRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductCategoryBrandRel record);
}