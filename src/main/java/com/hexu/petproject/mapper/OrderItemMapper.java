package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.OrderItem;

import java.util.List;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderItem record);

    List<OrderItem> selectOrderItemListById(Long id);
}