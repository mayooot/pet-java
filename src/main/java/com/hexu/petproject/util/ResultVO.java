package com.hexu.petproject.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname R
 * @Description 统一返回格式
 * @Author 86176
 * @Date 2022-03-31 18:23
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultVO<T> implements Serializable {

    private String code;
    private String msg;
    private T data;


    public static ResultVO ok(Object data) {
        return new ResultVO(ResultEnum.OK.getCode(), ResultEnum.OK.getMsg(), data);
    }

    public static ResultVO ok() {
        return new ResultVO(ResultEnum.OK.getCode(), ResultEnum.OK.getMsg(), null);
    }

    public static ResultVO errer(ResultEnum resultEnum) {
        return new ResultVO(resultEnum.getCode(), resultEnum.getMsg(), null);
    }

    public static ResultVO errer(String code, String msg) {
        return new ResultVO(code, msg, null);
    }
}
