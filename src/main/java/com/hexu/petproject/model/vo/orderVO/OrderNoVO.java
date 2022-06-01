package com.hexu.petproject.model.vo.orderVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname OrderNoVO
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-22 12:01
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderNoVO {
    private Long orderId;

    private String orderNo;

}
