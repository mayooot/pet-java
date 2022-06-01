package com.hexu.petproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @Classname Redisconfig
 * @Description  解决序列化乱码问题
 * @Author 86176
 * @Date 2021-12-06 10:02
 * @Version 1.0
 **/
@Configuration
public class RedisConfig {


    @Bean("redisTemplate")
    public RedisTemplate redisTemplate(@Lazy RedisConnectionFactory connectionFactory) {
        RedisTemplate redis = new RedisTemplate();

        GenericToStringSerializer<String> keySerializer = new GenericToStringSerializer<>(String.class);
        redis.setKeySerializer(keySerializer);
        redis.setHashKeySerializer(keySerializer);
        redis.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redis.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        redis.setConnectionFactory(connectionFactory);
        return redis;
    }

    /**
     * 缓存相关
     * @return
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        config=config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));
        return config;
    }

}
