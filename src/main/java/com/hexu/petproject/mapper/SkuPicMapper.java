package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.SkuPic;

public interface SkuPicMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SkuPic record);

    SkuPic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SkuPic record);
}