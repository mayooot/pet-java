package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.ProductAttributeRel;

public interface ProductAttributeRelMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ProductAttributeRel record);

    ProductAttributeRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductAttributeRel record);
}