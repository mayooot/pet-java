package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.SpecParam;

public interface SpecParamMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SpecParam record);

    SpecParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpecParam record);
}