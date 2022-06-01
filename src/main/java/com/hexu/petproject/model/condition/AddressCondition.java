package com.hexu.petproject.model.condition;

import lombok.Data;

/**
 * @Classname AddressCondition
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-11 20:08
 * @Version 1.0
 **/
@Data
public class AddressCondition {
    /**
     * 根据id添加或修改
     * @param id 地址id
     * @param userName 名称
     * @param phoneNumber 手机号
     * @param defaultStatus 是否默认地址
     * @param province 省
     * @param city 市
     * @param region 区
     * @param countyId 区id
     * @param detailAddress 地址
     * @param token 用户信息
     */
   private Long id;
    private  String userName;
    private   String phoneNumber;
    private   Integer defaultStatus;
    private  String province;
    private  String city;
    private    String region;
    private  Integer countyId;
    private  String detailAddress;
    private String token;
}
