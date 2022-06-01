package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.PayLog;

public interface PayLogMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(PayLog record);

    PayLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PayLog record);
}