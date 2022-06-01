package com.hexu.petproject.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexu.petproject.common.SystemConstants;
import com.hexu.petproject.config.ConfirmRabbitConfig;
import com.hexu.petproject.config.DelayOrderRabbitConfig;
import com.hexu.petproject.config.SnowFlakeUtil;
import com.hexu.petproject.exception.OmsException;
import com.hexu.petproject.mapper.CartMessMapper;
import com.hexu.petproject.mapper.OrderMapper;
import com.hexu.petproject.mapper.SkuMapper;
import com.hexu.petproject.model.condition.OrderSkuCondition;
import com.hexu.petproject.model.condition.SkuCondition;
import com.hexu.petproject.model.pojo.CartMess;
import com.hexu.petproject.model.pojo.Order;
import com.hexu.petproject.model.pojo.OrderItem;
import com.hexu.petproject.model.vo.attributeVO.ProductAttributeVO;
import com.hexu.petproject.model.vo.cartVO.CartVo;
import com.hexu.petproject.model.vo.orderVO.OrderDetailVO;
import com.hexu.petproject.model.vo.orderVO.OrderNoVO;
import com.hexu.petproject.model.vo.orderVO.OrderVO;
import com.hexu.petproject.service.OrderItemService;
import com.hexu.petproject.service.OrderService;
import com.hexu.petproject.service.ProductAttributeService;
import com.hexu.petproject.service.SkuService;
import com.hexu.petproject.util.RedisService;
import com.hexu.petproject.util.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname 订单功能Service
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-12 15:08
 * @Version 1.0
 **/
@Service
public class OrderServiceImpl implements OrderService {

    private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderItemService orderItemService;

    @Resource
    private ProductAttributeService productAttributeService;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private RedisService redisService;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private SkuService skuService;

    @Resource
    private CartMessMapper cartMessMapper;


    /**
     * 总金额，使用BigDecimal数据类型
     */
    BigDecimal totalMoney = new BigDecimal(0);

    /**
     * 生成预提交订单
     * 1. 判断购物车中的商品是否已经下架
     * 2. 判断购物车中的商品数量是否小于库存
     * 3. 计算出订单应付款 = 总金额 + 运费
     */
    @Override
    public OrderVO preSubmit(OrderSkuCondition orderConditions) {

        // 声明OrderVO对象，用于返回。
        OrderVO orderVO = new OrderVO();

        // 遍历OrderSkuCondition中的skuInfos（商品信息集合）
        List<CartVo> cartVos = orderConditions.getSkuInfos().stream().map(skuInfo -> {

            // 通过skuId获取CartVO（购物车数据展示对象）
            CartVo cartVo = skuMapper.selectCartVOBySkuId(skuInfo.getSkuId());

            if (ObjectUtils.isEmpty(cartVo)
                    || cartVo.getPublishStatus().equals(SystemConstants.Product.PRODUCT_IS_TAKE_DOWN)) {
                // 如果查出的CartVO为空或者CartVO的publishStatus属性为0，说明商品已经下架
                log.info("当前商品已经下架：{}", skuInfo.getSkuId());
                throw new OmsException(ResultEnum.PRODUCT_STATUS_OFF);
            }

            if (cartVo.getMax() < skuInfo.getSkuNum()) {
                //判断库存是否足够 CartVO中的Max对应sku表中的商品库存stock
                // 如果库存数量小于订单商品数量
                log.info("当前订单所购买数量已经超过该商品最大库存");
                throw new OmsException(ResultEnum.ADD_CART_LOW_STOCK_ERROR);
            }

            // 设置购物车对象的商品数量
            cartVo.setNum(skuInfo.getSkuNum());
            // 计算订单中某类商品的总价
            BigDecimal productTotalMoney = cartVo.getUnitPrice()
                    .multiply(BigDecimal.valueOf(skuInfo.getSkuNum()));

            // 设置总金额
            totalMoney = totalMoney.add(productTotalMoney);
            return cartVo;
        }).collect(Collectors.toList());


        // 设置订单总金额
        orderVO.setTotalMoney(totalMoney);

        // 运费为10元
        BigDecimal freight = BigDecimal.TEN;
        // 设置运费
        orderVO.setFreight(freight);

        // 设置应付款（总金额+运费）
        orderVO.setPayMoney(totalMoney.add(freight));

        // 设置OrderVO中的商品集合ProductList
        orderVO.setProductList(cartVos);

        // 将预先声明的总金额totalMoney清空
        totalMoney = BigDecimal.valueOf(0);

        return orderVO;
    }

    /**
     * 增加订单
     * 1. 生成订单
     * 2. 校验库存
     * 3. 生成订单明细
     * 4. 清空购物车
     * 5. 扣减库存
     * 6. 失败后或者未支付回滚库存
     */
    @Override
    // 出现异常时回滚
    @Transactional(rollbackFor = {OmsException.class, RuntimeException.class})
    public OrderNoVO add(OrderSkuCondition condition, String token) {

        // todo 1. 生成订单
        //创建订单
        Order order = new Order();

        // 使用BeanUtils将orderConditions中的属性赋值给order（赋值的属性有收货人姓名、手机号、收货地址）
        BeanUtils.copyProperties(condition, order);
        // 订单生成时间
        LocalDateTime nowDate = LocalDateTime.now();
        // 手动处理没有传递过来的参数
        // 设置订单所属用户Id
        Long userId = redisService.getUserByToken(token).getId();
        order.setUserId(userId);
        // 订单号为当前时间字符串
        String formatDateTime = nowDate.format(DateTimeFormatter
                .ofPattern("yyyyMMddHHmmssSSS"));
        // 设置订单号（当前时间的字符串）
        // order.setOrderNo(formatDateTime);
        // 使用雪花算法生成订单号
        order.setOrderNo(String.valueOf(SnowFlakeUtil.getId()));
        // 设置订单状态为代付款
        order.setOrderStatus(SystemConstants.Order.Status.OBLIGATION);
        // 设置创建时间
        order.setCreatedTime(LocalDateTime.now());
        // 设置支付状态为待支付
        order.setPayStatus(SystemConstants.Pay.PayStatus.WAIT);
        // 设置订单过期时间（30分钟）
        order.setExpirationTime(nowDate.plusMinutes(30));
        // 设置订单状态，为待支付
        order.setOrderStatus(SystemConstants.Pay.PayStatus.WAIT);
        // 设置支付状态, 为待付款
        order.setPayStatus(SystemConstants.Order.Status.OBLIGATION);
        // 将订单存入数据库
        orderMapper.insertSelective(order);

        // 声明总金额
        BigDecimal totalMoney = BigDecimal.ZERO;
        // 声明总数量
        Integer totalNum = 0;


        // todo 2. 校验库存
        // 获取订单的商品集合
        List<SkuCondition> skuInfos = condition.getSkuInfos();
        // 遍历商品集合
        for (SkuCondition skuInfo : skuInfos) {
            Long skuId = skuInfo.getSkuId();
            // 根据商品Id，查询出对应的sku对象
            CartVo cartVo = skuMapper.selectCartVOBySkuId(skuId);

            // 如果当前订单中的商品大于该商品的库存
            if (skuInfo.getSkuNum() > cartVo.getMax()) {
                log.info("当前规格商品库存不足：{}", skuId);
                throw new OmsException(ResultEnum.ADD_CART_LOW_STOCK_ERROR);
            }

            // 处理总数量
            totalNum += skuInfo.getSkuNum();
            // 处理总金额
            totalMoney = totalMoney.add(cartVo.getUnitPrice()
                    .multiply(new BigDecimal(skuInfo.getSkuNum())));

            // todo 3. 生成订单明细表
            OrderItem orderItem = new OrderItem();
            // 绑定对应的订单Id
            orderItem.setOrderId(order.getId());
            // 设置创建时间
            orderItem.setCreatedTime(nowDate);
            // 设置商品图片路径
            orderItem.setProductPic(cartVo.getPicUrl());
            // 设置商品销售单价
            orderItem.setProductPrice(cartVo.getUnitPrice());
            // 设置购买数量
            orderItem.setProductQuantity(skuInfo.getSkuNum());
            // 设置skuId
            orderItem.setSkuId(skuInfo.getSkuId());
            // 设置spuId
            orderItem.setSpuId(cartVo.getSpuId());

            // 设置商品销售属性
            // 根据商品Id获取到对应的商品属性，然后处理成json字符串
            List<ProductAttributeVO> attributeVOList = productAttributeService
                    .getAttributeVOBySpuId(cartVo.getSpuId());
            String productAttr = null;
            try {
                productAttr = objectMapper.writeValueAsString(attributeVOList);
            } catch (JsonProcessingException e) {
                log.error("序列化商品属性异常");
                throw new OmsException(ResultEnum.COMMON_EXCEPTION);
            }
            orderItem.setProductAttr(productAttr);
            // 将订单明细存入数据库
            orderItemService.saveOrderItem(orderItem);

            // todo 4. 清空购物车
            // 使用rabbitmq发布确定模式清空购物车
            List<Long> message = new ArrayList<>(2);
            // 用户ID
            message.add(userId);
            // 商品ID
            message.add(skuId);

            // =======消息入库======
            // 发送消息前，先将消息入库
            // 入库消息对象
            CartMess mess = new CartMess();
            mess.setUserid(userId);
            mess.setSkuid(skuId);
            // 设置状态为0，代表生产端将消息发送给了RabbitMQ但还没收到确认
            mess.setStatus(0);
            // 插入到数据库中
            cartMessMapper.insertSelective(mess);
            // 获取刚刚插入到数据库中的消息对象id
            Long messId = cartMessMapper.selectIdBySkuidAndUseridAndUserid(skuId, userId);
            log.warn("消息入库成功，消息ID为{}", messId);

            // 用于发布确认回调的对象
            try {
                CorrelationData correlationData = new CorrelationData(String.valueOf(messId));
                rabbitTemplate.convertAndSend(ConfirmRabbitConfig.CART_CONFIRM_EXCHANGE_NAME,
                        ConfirmRabbitConfig.CART_CONFIRM_ROUTING_KEY,
                        message,
                        correlationData);
                log.info("订单生成后，发送消息给rabbitmq，删除用户购物车信息。用户ID:{},商品ID:{}", message.get(0), message.get(1));
            } catch (Exception e) {
                throw new OmsException(ResultEnum.DELETE_CART_ERROR);
            }


            // todo 5. 扣减库存
            int deductedResult = skuService.deductedInventory(skuId, skuInfo.getSkuNum());
            if (deductedResult == 0) {
                log.error("扣减库存失败");
                throw new OmsException(ResultEnum.REDUCE_INVENTORY_ERROR);
            }
        }

        // 更新当前订单的总金额和总数量
        order.setTotalMoney(totalMoney);
        order.setTotalNum(totalNum);
        order.setPayMoney(totalMoney);
        // 更新订单
        orderMapper.updateByPrimaryKeySelective(order);

        // todo 6. 失败后或者未支付回滚库存
        rabbitTemplate.convertAndSend(DelayOrderRabbitConfig.ORDER_EXCHANGE_NAME,
                DelayOrderRabbitConfig.ORDER_ROUTING_KEY,
                order.getId().toString(),
                message -> {
                    //指定超时时间
                    message.getMessageProperties().setExpiration(SystemConstants.ORDER_OVER_TIME);
                    return message;
                });

        return new OrderNoVO(order.getId(), order.getOrderNo());
    }

    /**
     * 查询订单详情接口
     *
     * @param orderNo 订单id
     * @param token   用户信息
     * @return
     */
    @Override
    public OrderDetailVO detail(String orderNo, String token) {
        Order order = orderMapper.detail(orderNo);
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        BeanUtils.copyProperties(order, orderDetailVO);
        log.info(orderDetailVO.toString());
        return orderDetailVO;
    }
}
