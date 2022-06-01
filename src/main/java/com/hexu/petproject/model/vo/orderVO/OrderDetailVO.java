package com.hexu.petproject.model.vo.orderVO;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Classname OrderDetailVO
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-22 11:44
 * @Version 1.0
 **/
@Data
public class OrderDetailVO implements Serializable {

    private Long id;
    private String orderNo;
    private BigDecimal payMoney;
    private String expirationTime;
    private Integer orderStatus;
    private Integer payStatus;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;


}
