package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.PcdDic;
import com.hexu.petproject.model.pojo.ReceiverAddress;

import java.util.List;

public interface PcdDicMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(PcdDic record);

    PcdDic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PcdDic record);
    List<PcdDic> list();
}