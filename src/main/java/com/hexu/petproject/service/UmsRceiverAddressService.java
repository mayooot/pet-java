package com.hexu.petproject.service;

import com.hexu.petproject.model.condition.AddressCondition;
import com.hexu.petproject.model.vo.pcdDicVO.DicVO;
import com.hexu.petproject.model.vo.receiverAddressVO.ListVO;

import java.util.List;

/**
 * @Classname ReceiverAddressService
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-09 15:59
 * @Version 1.0
 **/
public interface UmsRceiverAddressService {
    /**
     * 三级
     * @return
     */
    List<DicVO> dicList();

    /**
     * 查询用户收货地址
     * @param id 用户id
     * @return
     */
     List<ListVO> list(Integer userId, String id);

    /**
     * 删除用户地址
     * @param addressId 地址编号
     * @param token
     * @return
     */
    Boolean remove(Long addressId, String token);


    void saveOrUpdate(AddressCondition addressCondition);
}

