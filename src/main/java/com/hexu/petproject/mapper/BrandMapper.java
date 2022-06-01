package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.Brand;

import java.util.List;

public interface BrandMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Brand record);

    Brand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Brand record);

    /**
     * 查询所有品牌
     */
    List<Brand> list();
}