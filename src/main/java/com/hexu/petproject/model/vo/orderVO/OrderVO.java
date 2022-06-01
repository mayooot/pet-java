package com.hexu.petproject.model.vo.orderVO;

import com.hexu.petproject.model.vo.cartVO.CartVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Classname 订单展示对象
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-12 15:04
 * @Version 1.0
 **/
@Data
public class OrderVO {

    /**
     * 订单总金额
     */
    private BigDecimal totalMoney;

    /**
     * 订单应付款
     */
    private BigDecimal payMoney;

    /**
     * 运费
     */
    private BigDecimal freight;

    /**
     * 订单中的商品集合
     */
    private List<CartVo> productList;


}
