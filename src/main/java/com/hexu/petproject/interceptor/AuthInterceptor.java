package com.hexu.petproject.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexu.petproject.model.pojo.User;
import com.hexu.petproject.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @Classname AuthCustomInterceptors
 * @Description 权限自定义拦截器
 * @Author 86176
 * @Date 2022-04-08 10:51
 * @Version 1.0
 **/
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // log.info("有个请求进来了,叫{}", request.getRequestURI());
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //查询对应的请求上有木有对应的注解   有的话说明当前请求不需要验证  直接放行
        Authentication methodAnnotation = handlerMethod.getMethod().getAnnotation(Authentication.class);
        if (!StringUtils.isEmpty(methodAnnotation)) {
            log.warn("当前方法不需要验证   直接放行");
            return true;
        }
        //获取请求头中的token  只有登陆后才会有
        String token = request.getHeader("token");
        //判断请求头中是否有用户的token  没有的话直接拦截
        if (!StringUtils.hasText(token)) {
            log.warn("请求头没有token，进行拦截");
            HashMap<String, String> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("code", "401");
            objectObjectHashMap.put("msg", "用户权限校验失败");
            response.setContentType("application/json; charset=UTF-8");
            //发送错误响应JSON
            response.getWriter().print(objectMapper.writeValueAsString(objectObjectHashMap));
            return false;
        }
        //判断用户信息是否过期
        User o = (User) redisTemplate.opsForValue().get(RedisUtils.send(token));
        if (null == o) {
            log.warn("拿到了token  但是没有数据或者已过期  拦截");
            return false;
        }
        return true;
    }
}
