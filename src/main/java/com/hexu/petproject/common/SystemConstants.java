package com.hexu.petproject.common;

import java.io.Serializable;

/**
 * @Classname SystemConstants
 * @Description 商品排序常量
 * @Author 86176
 * @Date 2022-03-31 18:16
 * @Version 1.0
 **/
public class SystemConstants implements Serializable {

    // 阿里云OSS文件前缀
    public final static String OSS_PICTURE_PREFIX = "https://pet-project-imgage.oss-cn-beijing.aliyuncs.com/";

    // 订单过期时间（默认为30分钟）
    public final static String ORDER_OVER_TIME = "1800000";


    /**
     * 商品相关常量
     */
    public static class Product {
        public final static Integer PRODUCT_IS_TAKE_DOWN  = 0;
    }

    /**
     * 查询数据的排序方式
     */
    static class SORE_TYPE {
        /**
         * 默认
         */
        final static Integer DEFAULT = 1;

        /**
         * 销量
         */
        final static Integer SALES = 2;

        /**
         * 评论
         */
        final static Integer COMMENT = 3;
    }

    /**
     * 订单相关常量
     */
    public static class Order {

        /**
         * 个人最大收货地址数量
         */
        public static Integer RECEIVER_ADDRESS_MAX = 20;
        /**
         * 收货人姓名长度限制
         */
        public static Integer USERNAME_LENGTH_MAX = 10;
        /**
         * 收货详细地址长度限制
         */
        public static Integer ADDRESS_LENGTH_MAX = 200;

        /**
         * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->超时
         */
        public static class Status {
            /**
             * 待付款
             */
            public static Integer OBLIGATION = 0;
            /**
             * 待发货
             */
            public static Integer WAIT_DELIVERY = 1;
            /**
             * 已发货
             */
            public static Integer DELIVERY = 2;
            /**
             * 已完成
             */
            public static Integer FINISH = 3;
            /**
             * 已关闭
             */
            public static Integer CLOSE = 4;
            /**
             * 超时
             */
            public static Integer TIMEOUT = 5;
        }

        /**
         * 支付状态
         */
        public static class PayStatus {
            /**
             * 待支付
             */
            public static Integer WAIT = 0;
            /**
             * 支付成功
             */
            public static Integer SUCCESS = 1;
            /**
             * 支付失败
             */
            public static Integer FAIL = 2;
        }

        /**
         * 支付方式
         */
        public static class PayWay {
            /**
             * 在线支付
             */
            public static Integer ONLINE = 0;
            /**
             * 货到付款
             */
            public static Integer CASH_ON_DELIVERY = 1;
        }


    }

    /**
     * 支付相关常量
     */
    public static class Pay {
        /**
         * 支付订单号追加字符长度
         */
        public static int ORDER_NO_APPEND_SIZE = 6;

        /**
         * 支付方式
         */
        public static class PayWay {
            /**
             * 阿里支付
             */
            public static Integer ALI = 1;
            /**
             * 微信支付
             */
            public static Integer WECHAT = 2;
        }

        /**
         * 支付状态
         */
        public static class PayStatus {
            /**
             * 待支付
             */
            public static Integer WAIT = 0;
            /**
             * 支付成功
             */
            public static Integer SUCCESS = 1;
            /**
             * 支付失败
             */
            public static Integer FAIL = 2;
        }

        /**
         * 支付记录状态
         */
        public static class PayLogStatus {
            /**
             * 成功
             */
            public static Integer SUCCESS = 1;
            /**
             * 失败
             */
            public static Integer FAIL = 2;
        }
    }
}
