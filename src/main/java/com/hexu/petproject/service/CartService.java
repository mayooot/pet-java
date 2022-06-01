package com.hexu.petproject.service;

import com.hexu.petproject.model.vo.cartVO.CartVo;

import java.util.List;

/**
 * @Classname CartService
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-09 14:27
 * @Version 1.0
 **/
public interface CartService {
    /**
     * 添加商品到redis购物车
     * @param skuId 商品编号
     * @param num 数量
     * @param token 用户token
     * @return
     */
    void insertCart(Long skuId,Integer num,String token);

    /**
     * 删除购物车的商品
     * @param skuIds 商品id ,号隔开
     * @param token 用户token
     */
    void remove(String skuIds, String token);

    /**
     * 查询用户的购物车信息
     * @param token 用户token
     */
    List<Object> cartList(String token);

    /**
     * 查询用户购物车商品的数量
     * @param token 用户token
     * @return
     */
    Long cartCount(String token);
}
