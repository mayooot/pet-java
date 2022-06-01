package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.ProductDetailPic;

public interface ProductDetailPicMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ProductDetailPic record);

    ProductDetailPic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductDetailPic record);
}