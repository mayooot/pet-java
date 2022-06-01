package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    Order detail(String orderNo);
}