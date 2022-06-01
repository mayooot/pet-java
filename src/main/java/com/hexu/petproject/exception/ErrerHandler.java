package com.hexu.petproject.exception;

import com.hexu.petproject.util.ResultVO;
import com.hexu.petproject.util.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Classname ErrerHandler
 * @Description 统一异常处理
 * @Author 86176
 * @Date 2021-12-08 15:24
 * @Version 1.0
 **/
@RestControllerAdvice
@Slf4j
public class ErrerHandler {
    @ExceptionHandler(value = UmsException.class)
    public ResultVO errer2(UmsException e) {
        log.error("报错 {}，{}",e.getMessage(),e);
        return ResultVO.errer(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(value = CartException.class)
    public ResultVO errer4(CartException e) {
        log.error("报错 {}，{}",e.getMessage(),e);
        return ResultVO.errer(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(value = AssertionError.class)
    public ResultVO errer3(AssertionError e) {
        log.error("报错 {}，{}",e.getMessage(),e);
        return ResultVO.errer("500", e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResultVO errer(Exception e) {
        log.error("系统异常 {}，{}",e.getMessage(),e);
        return ResultVO.errer(ResultEnum.ERROR);
    }
}