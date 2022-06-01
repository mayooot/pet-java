package com.hexu.petproject.config;



import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Classname AlipayConfig
 * @Description 支付宝支付配置
 * @Author 86176
 * @Date 2022-04-22 12:27
 * @Version 1.0
 **/
@Configuration
@Data
@Component
public class AlipayConfig {

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    private String appId = "2021000118650984";

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    private String privateKey = "MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCvG1exl+hFJ/6iLn0hA/19hwbXEDVqYL5XoIy/e3N1wzQwBs7bYAzTgXupPUuVH6oByyvpE1aB5+OfFls620WhRl9tzLfvD0EhXJkKUrE7rUmLtVLjnPvJ3uoK+o4i/5950W2D+zpqqIZQNKtXZZxV3iDfa3LJ0YB4+IjJmsIQs51hQTh4q3aycdvFHx7Qs2FnFsVqNzAojZDYmNvX471lSPRF7lw4qja3XHvRQ1SN4NqmsZkQT6dHOHStqAgGrbSqwn/SFkZuIGOyzvzuich8Br+vpH6WzXbHlUzF+uFw+D1/8C4eH+f3TFHWMbxdPPxXkcL9SejRkgCjNje0d7tBAgMBAAECgf8OXS/UYaGsCpvYsfeRvcINigYFnk7DaywyA/mHu5i9EEY3qldNp5NlxjleqKiKkw5fF0uC0xNFlMAXTL4vFc2gbSVm3GQ6OAYftnjXX/Ep9A0UaMtj4HzHEoIgZG1EcbOjSoqxSOqB1VEvQyLLCIrKgG4x5Z6E76qaQcj2wLsqo25dLgkpI2Xc6R2SO6sTdODrPoInbvePUiLc9zE+JkPakj01KxY7xyEsvjLpXaiFc4HUhtIhLfyrLVcIylz0mEhal7cmNHUksnjCOPxdRPxhuhGEXDsG/erBkESZRY+f6Fk0kX6ZTYVb/m6GBHK21hg6+gJBQnlgNPXCN/H+ifECgYEA6deQBpFT/apwWMmYm9Zwrsv0cSAKX3RINQj2wt4dxx7oMlTUcdbr9BQQd5z7qLzprj5mWgEaov34Fdy8JNXJcKfNiaOt4RK03y9GGqZ7dwu1SXcjjuJ+CRY0MyoY3hfvxqU8AcBoXcuwl9yerVSsaEsoPdBSWslBebrGRkWaYUUCgYEAv7MBp6hEeQQ+ug+oKeBbodaXBkriMk8836nSLtEI6gtr0NPA/d1MHqTZv0XG0cNwu0NPgDPF4ZBk1iSqFwzVLEFuBYS2VOvRu+jI84HlekMPBYicZOJcTDGdwkwpNV+Csm4A+ZDzCDGF5G4BHn8An20RRWalhkkn3KlO+wdwa80CgYA5grCDTP6xJlfXHk2QFh9EfhiyMcUbUMfpczjtGB+Ot0U4C7DsBoWlU8HxCQTDrSJznhkiDZ91cqyvA8ip7oJKWJquPTkoX6doNIIzT7qRvqlpVgnCvGVu6q0P4EyZYp2v5zdo3t9MIipeTv4fdvGn/MP6399BQLn/rDXTVo7vWQKBgG89YwJ1RHatHfE+UKksRkAdlDnUjnn/PSXVF+CO8ItP4Vqm5mDqX9eQdcLmrL7gmLQyFRoh8Pw5nCof0IvbqIAr37pYDq4sweI27RVRFWbjLv5ZDdgE+uZEDHXJWEheiiscZnhrwnvIsRejNgcKT8QzuEf7jqXkIbuMjAZjhLspAoGBAJR461nZi/g61j3zLKZP2GSEg9eOXWbK4uco29Bc6xDENFEE+CL5QDD/15NQ31/bUN9nS6IVMDNZiP6ZI2V2JSrqKPjbGsYCEpZoCEkjNkHwDiAhOVEXTdvZ9UcouKtn9tFGWNn+gXklTXGNG2OrtwCSvNZ8P1EX7BTSY7Cc7MGA";

    /**
     * 支付宝公钥,
     */
    private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhedGWF+K2qrR0TZ+LSWAV64CJbuiIKQ543TKsH6tEY+38K04z8PK1zEDGXIw+g0ke2Eu7CpsLKBw+WSmgZYAHNJPKY3MM+YwMmb0s1YuGyi8EghcmPyQIcwKi4G/5A3Pz/i/y94YH89Q96cJ8ph/PGpwGzj7lHmiXBlmxLbTt141kUTSvDVgUsdVCO0Aco1202U6ERaf45TCNZbkUx/RSEkjL8WyRjtn+pIipG83X+PaC0lu/FK2DHq66fh/CrahtH4oVd86h7Ni773qBl/VqZ8XpShCBJUTGT/10syr7DB8IqpqPTJ6/aeRXS2UBBoLhM/ce41MJ2n1dqP6TlG/xwIDAQAB";

    /**
     * 服务器异步通知页面路径需http://格式的完整路径，不能加?id=123这类自定义参数
     */
    private String notifyUrl = "http://127.0.0.1/api/oms/pay/aliPay/notify";

    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径.
     * 支付完成后返回的地址
     */
    private String returnUrl = "http://127.0.0.1/api/oms/pay/aliPay/return";

    /**
     * 签名方式
     */
    private String signType = "RSA2";

    /**
     * 字符编码格式
     */
    private String charset = "utf-8";

    /**
     * 支付宝网关
     */
    private String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 支付宝网关
     */
    private String logPath = "C:\\";
}

