package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.ReceiverAddress;
import com.hexu.petproject.model.vo.receiverAddressVO.ListVO;

import java.util.List;

public interface ReceiverAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ReceiverAddress record);

    ReceiverAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReceiverAddress record);

    List<ReceiverAddress> getByUserId(Integer userId);
}