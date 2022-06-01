package com.hexu.petproject.model.vo.userVO;

import lombok.Data;

/**
 * @Classname UserVO
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-06 16:30
 * @Version 1.0
 **/
@Data
public class UserVO {
    private Long id;
    private String account;
    private String openId;
    private String userName;
}
