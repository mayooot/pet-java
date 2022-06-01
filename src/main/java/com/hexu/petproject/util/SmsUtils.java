package com.hexu.petproject.util;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexu.petproject.config.AiliyunSmsConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @Classname SmsUtils
 * @Description 短信服务通用配置
 * @Author 86176
 * @Date 2022-04-05 11:17
 * @Version 1.0
 **/
public class SmsUtils {
    /**
     * 验证前端传过来的手机号格式是否正确
     * @param phone 手机号
     * @return true/false
     */
    public static Boolean checkPhone(String phone){
        boolean present = Optional.ofNullable(phone).isPresent();
        return present && Pattern.matches("^1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$", phone);

    }
    /**
     * 生成6位数验证码
     * @param codeType 类型
     * @return 验证码
     */
    public static Integer code(Integer codeType){
        if (codeType == 1) {
            return (int) ((Math.random() * 9 + 1) * 100000);
        }
        return null;
    }

    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = AiliyunSmsConfig.ENDPOINT;
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    /**
     * 发送验证码
     * @param phone 手机号
     * @param code 6位数验证码
     */
    public static void x(String phone,Integer code) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Integer> map = new HashMap<>();
        map.put("code", code);
        String code6 = objectMapper.writeValueAsString(map);
        com.aliyun.dysmsapi20170525.Client client = createClient(AiliyunSmsConfig.ACCESSKEYID, AiliyunSmsConfig.ACCESSKEYSECRET);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName(AiliyunSmsConfig.SIGNNAME)
                .setTemplateCode(AiliyunSmsConfig.TEMPLATECODE)
                .setPhoneNumbers(phone)
                .setTemplateParam(code6);
        // 复制代码运行请自行打印 API 的返回值
        client.sendSms(sendSmsRequest);
    }
    public static String token(HttpServletRequest request){
        return request.getHeader("token");
    }
}
