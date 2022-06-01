package com.hexu.petproject.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Classname RestultEnum
 * @Description 统一返回枚举类
 * @Author 86176
 * @Date 2022-04-05 11:56
 * @Version 1.0
 **/
@Getter
@AllArgsConstructor
public enum ResultEnum {
    MALFORMED_PHONE_NUMBER("10001", "手机号格式错误"),
    FAILED_TO_SEND_VERIFICATION_CODE("10002", "验证码发送失败"),
    VERIFICATION_CODES_ARE_SENT_FREQUENTLY("10003", "验证码发送频繁"),
    VERIFICATION_CODE_VERIFICATION_FAILED("10004", "验证码验证失败"),
    USER_PERMISSION_VERIFICATION_FAILED_PROCEDURE("401", "用户权限校验失败"),
    CART_QUANTITY_MUST_BE_GREATER_THAN_0("30001", "购物车数量必须大于0"),
    NOT_ENOUGH_STOCK("30002", "库存不够"),
    OK("200", "请求成功"),
    ERROR("0", "未知错误"),
    PRODUCT_SEARCH_ERROR("20001", "商品搜索报错"),


    PHONE_NUMBER_NOT_FORMAT("10001", "手机号格式不符合要求"),
    SEND_VARI_CODE_FAIL("10002", "验证码发送失败"),
    SEND_VARI_CODE_FAST("10003", "验证码短信发送频率过高"),
    INCORRECT_OR_INVALID_VERIFICATION_CODE("10004", "验证码错误或失效"),
    AUTH_TOKEN_EMPTY("10005", "用户token为空"),

    COMMON_EXCEPTION("400", "系统异常"),


    /**
     * 商品相关
     */
    PRODUCT_DATA_IS_EMPTY("20001", "商品数据为空"),
    MENU_LIST_IS_EMPTY("20002", "菜单列表为空"),
    BRAND_LIST_IS_EMPTY("20003", "品牌列表为空"),
    PRODUCT_ATTRIBUTE_DATA_IS_EMPTY("20004", "商品属性数据为空"),
    PRODUCT_INFORMATION_DOES_NOT_EXIST("20005", "对应的商品信息不存在"),
    PRODUCT_STATUS_OFF("20006", "商品已下架"),
    PRODUCT_TOO_MANY_BUYERS("20007", "购买人数过多，等会再试试吧~"),

    /**
     * 购物车相关
     */
    ADD_CART_MIN_NUM_ERROR("30001", "至少需要向购物车中加入1件商品"),
    ADD_CART_LOW_STOCK_ERROR("30002", "库存不足"),

    /**
     * 订单相关
     */
    ORDER_NOT_EXIST("40001", "订单不存在"),
    ORDER_STATUS_ERROR("40002", "订单状态错误"),
    REVEIVER_ADDRESS_MAX_ERROR("40001", "最多可以添加20个收货地址"),
    REVEIVER_ADDRESS_UNSERNAME_TO_LANG_ERROR("41002", "收货人姓名应在10个字符之内"),
    REVEIVER_ADDRESS_DETAIL_TO_LANG_ERROR("41003", "详细收货地址应保持在200个字符之内"),
    REVEIVER_ADDRESS_NOT_EXIST_ERROR("41004", "当前收货地址不存在"),
    REDUCE_INVENTORY_ERROR("40003", "扣减商品SKU库存失败"),
    DELETE_CART_ERROR("40004", "清空购物车失败"),


    /**
     * 订单支付
     */
    PAY_ERROR("50001", "支付失败"),
    PAY_REPEAT("5002", "请勿重复支付/提交"),

    /**
     * 其他
     */
    PARAM_ERROR("91001", "请求参数错误！"),
    OTHER_FILE_SIZE_MAX_ERROR("91002", "上传文件过大"),
    OTHER_FILE_NOT_EMPTY_ERROR("91003", "不能上传空文件"),
    ;


    private final String code;
    private final String msg;

}
