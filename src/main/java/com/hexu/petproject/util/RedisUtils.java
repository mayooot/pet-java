package com.hexu.petproject.util;

/**
 * @Classname RedisUtils
 * @Description redis工具
 * @Author 86176
 * @Date 2022-04-06 15:08
 * @Version 1.0
 **/
public class RedisUtils {


    /**
     * 用于商品秒杀时存于Redis中的key
     *
     * @param skuId 商品id
     * @return redis key
     */
    public static String miaoshaRedisKey(Long skuId) {
        return "pet:miaosha:" + skuId;
    }

    /**
     * 用于生成商品购买时存于Redis中的value
     *
     * @param threadId  线程id
     * @param timestamp 当前时间戳
     * @return redis value
     */
    public static String productRedisValue(Long threadId, String timestamp) {
        return "oversold:" + threadId + ":" + timestamp;
    }

    /**
     * 用于生成商品购买时存于Redis中的key
     *
     * @param skuId 商品id
     * @return redis key
     */
    public static String productRedisKey(String skuId) {
        return "pet:product:" + skuId;
    }


    /**
     * 用于生成支付幂等性存于Redis中的key
     *
     * @param id 订单号
     * @return redis key
     */
    public static String payRedisKey(String id) {
        return "pet:pay:" + id;
    }

    /**
     * 用于生成短信验证码存于Redis中的key
     *
     * @param phone    手机号
     * @param codeType 类型
     * @return redis key
     */
    public static String smsRedisKey(String phone, Integer codeType) {
        return "pet:sms:" + phone + ":" + codeType.intValue();
    }

    /**
     * 用于生成用户信息存于Redis中的key
     *
     * @param token
     * @return redis key
     */
    public static String tokenRedisKey(String token) {
        return "pet:token:" + token;
    }

    /**
     * 用于生成用户购物车信息存于Redis中的key
     *
     * @param userId 用户ID
     * @return redis key
     */
    public static String cartRedisKey(String userId) {
        return "pet:cart:" + userId;
    }


    /**
     * 短信key值规范
     *
     * @param phone    手机号
     * @param codeType 类型
     * @return
     */
    @Deprecated
    public static String send(String phone, Integer codeType) {
        return "pet:sms:" + phone + ":" + codeType;
    }

    /**
     * token值规范
     *
     * @param token UUID
     * @return
     */
    @Deprecated
    public static String send(String token) {
        return "pet:token:" + token;
    }

    /**
     * token值规范
     *
     * @param token UUID
     * @return
     */
    @Deprecated
    public static String send(Long token) {
        return "pet:cart:" + token;
    }


}
