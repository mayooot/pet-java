package com.hexu.petproject.service.impl;

import com.hexu.petproject.mapper.OrderItemMapper;
import com.hexu.petproject.model.condition.OrderSkuCondition;
import com.hexu.petproject.model.pojo.Order;
import com.hexu.petproject.model.pojo.OrderItem;
import com.hexu.petproject.model.vo.cartVO.CartVo;
import com.hexu.petproject.service.OrderItemService;
import com.hexu.petproject.service.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Classname OrderItemServiceImpl
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-14 11:33
 * @Version 1.0
 **/
@Slf4j
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Resource
    private SkuService skuService;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    private CartServiceImpl cartServiceImpl;

    /**
     * 增加商品明细
     *
     * @param order           订单
     * @param orderConditions
     * @return
     */
    @Override
    public int insertOrderItem(Order order, OrderSkuCondition orderConditions, String token) {
        StringBuilder sb = new StringBuilder();
        orderConditions.getSkuInfos().forEach(item -> {
            CartVo cartByCartVo = skuService.getCartByCartVo(item.getSkuId());
            //判断数量是否充足
            if (item.getSkuNum() > cartByCartVo.getMax()) {
                return;
            }
            //增加商品明细
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setSpuId(cartByCartVo.getSpuId());
            orderItem.setProductPic(cartByCartVo.getPicUrl());
            orderItem.setProductPrice(cartByCartVo.getUnitPrice().multiply(BigDecimal.valueOf(item.getSkuNum())));
            orderItem.setProductQuantity(item.getSkuNum());
            orderItem.setSkuId(item.getSkuId());
            orderItem.setProductName(cartByCartVo.getTitle());
            orderItem.setProductAttr(cartByCartVo.getProductSkuSpecification());
            orderItem.setCreatedTime(LocalDateTime.now());
            orderItemMapper.insertSelective(orderItem);
            //扣除sku对应的库存
            skuService.delTheInventory(item.getSkuId(), item.getSkuNum());
            sb.append(item.getSkuId()).append(",");
        });
        log.info(sb.toString());
        //生成订单明细后输出购物车数据
        cartServiceImpl.remove(String.valueOf(sb), token);
        return 0;
    }

    @Override
    public void rollBackTheInventory(Order order) {
        // 获取该订单下的所有商品
        List<OrderItem> orderList = orderItemMapper.selectOrderItemListById(order.getId());

        orderList.forEach(orderItem -> {
            log.info("订单信息为" + orderItem);
            skuService.rollBackTheInventory(orderItem);
        });
    }

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        return orderItemMapper.insertSelective(orderItem);
    }
}
