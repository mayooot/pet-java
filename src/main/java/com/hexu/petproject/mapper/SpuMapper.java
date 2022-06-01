package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.Spu;

public interface SpuMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Spu record);

    Spu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Spu record);

}