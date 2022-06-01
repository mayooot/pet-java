package com.hexu.petproject.service.impl;

import com.hexu.petproject.exception.CartException;
import com.hexu.petproject.mapper.CartMapper;
import com.hexu.petproject.mapper.SkuMapper;
import com.hexu.petproject.model.pojo.Cart;
import com.hexu.petproject.model.pojo.User;
import com.hexu.petproject.model.vo.cartVO.CartVo;
import com.hexu.petproject.service.CartService;
import com.hexu.petproject.util.RedisService;
import com.hexu.petproject.util.RedisUtils;
import com.hexu.petproject.util.ResultEnum;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Classname CartServiceImpl
 * @Description 购物车功能
 * @Author 86176
 * @Date 2022-04-09 14:28
 * @Version 1.0
 **/
@Service
public class CartServiceImpl implements CartService {

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private CartMapper cartMapper;

    @Resource
    private RedisService redisService;

    /**
     * 添加商品到redis购物车
     *
     * @param skuId 商品编号
     * @param num   数量
     * @param token 用户token
     */
    @Override
    public void insertCart(Long skuId, Integer num, String token) {
        if (num <= 0) {
            //商品购买数量小于0
            throw new CartException(ResultEnum.ADD_CART_MIN_NUM_ERROR);
        }
        //获取对应的商品信息
        CartVo cartByCartVo = skuMapper.selectCartVOBySkuId(skuId);

        if (num > cartByCartVo.getMax()) {
            // 商品购买数量大于该商品的库存
            throw new CartException(ResultEnum.ADD_CART_LOW_STOCK_ERROR);
        }

        cartByCartVo.setNum(num);
        BigDecimal bigDecimal = cartByCartVo.getUnitPrice().multiply(BigDecimal.valueOf(num));

        cartByCartVo.setPrice(bigDecimal);
        //获取用户编号
        String userId = userId(token);
        //添加到redis购物车里面去
        redisTemplate.opsForHash().put(userId, skuId, cartByCartVo);

        // ===============================
        // 保证redis缓存和数据库一致性
        // 用户添加商品到购物车的时候，会插入到数据库中
        cartMapper.insertSelective(new Cart(redisService.getUserByToken(token).getId(), skuId, num));

    }

    /**
     * 删除购物车的商品
     *
     * @param skuIds 商品id
     * @param token  用户token
     */
    @Override
    public void remove(String skuIds, String token) {
        //获取用户编号
        String userId = userId(token);
        Stream.of(skuIds)
                //获取所有商品编号
                .map(k -> k.split(","))
                //获取用户编号
                .flatMap(Arrays::stream).forEach(id -> {
            //进行删除
            redisTemplate.opsForHash().delete(userId, id);

            // ===============================
            // 保证redis缓存和数据库一致性
            // 用户添加商品都购物车的时候，会插入到数据库中
            cartMapper.deleteByUseridAndSkuid(redisService.getUserByToken(token).getId(), Long.valueOf(id));
        });



    }

    /**
     * 查询用户购物车信息
     *
     * @param token 用户token
     */
    @Override
    public List<Object> cartList(String token) {
        //获取用户编号
        String userId = userId(token);
        //获取用户下的所有商品
        return redisTemplate.opsForHash().values(userId);
    }

    /**
     * 查询用户购物车商品的数量
     *
     * @param token 用户token
     * @return
     */
    @Override
    public Long cartCount(String token) {
        String totalCount = userId(token);
        return redisTemplate.opsForHash().size(totalCount);
    }

    //辅助方法  获取用户的编号
    private String userId(String token) {
        //获取用户信息
        String user = RedisUtils.send(token);
        User o = (User) redisTemplate.opsForValue().get(user);
        //获取拼接的用户编号
        assert o != null;
        return RedisUtils.send(o.getId());
    }
}
