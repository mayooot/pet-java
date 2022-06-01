package com.hexu.petproject.exception;


import com.hexu.petproject.util.ResultEnum;

/**
 * @Classname ErrerException
 * @Description 用户的自定义异常
 * @Author 86176
 * @Date 2021-12-08 15:22
 * @Version 1.0
 **/
public class UmsException extends RuntimeException{
    private String code;
    public UmsException(ResultEnum emun) {
        super(emun.getMsg());
        this.code = emun.getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
