package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    /**
     * 根据手机号查询是否注册过
     * @param phone 手机号
     * @return
     */
    User getByPhoneOne(String phone);

    User getByOpIdOne(String openID);
}