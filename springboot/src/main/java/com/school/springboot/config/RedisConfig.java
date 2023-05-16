package com.school.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.alibaba.fastjson2.support.spring.data.redis.GenericFastJsonRedisSerializer;

@Configuration
@SuppressWarnings("all")
public class RedisConfig {

    @Primary
    @Bean("redisTemplate")
    public RedisTemplate<String, Object> getThirdRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(connectionFactory);
        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        // KeyStringRedisSerializer keyStringRedisSerializer = new KeyStringRedisSerializer();
        // JdkSerializationRedisSerializer stringRedisSerializer = new JdkSerializationRedisSerializer();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer(); 
        GenericToStringSerializer toStringSerializer = new GenericToStringSerializer<>(getClass());
        redisTemplate.setValueSerializer(toStringSerializer);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        return redisTemplate;
    }

    // @Bean("cacheManager")
    // @Primary
    // public RedisCacheManager getCacheManager(RedisConnectionFactory redisConnectionFactory) {
    //     RedisCacheWriter cacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
    //     StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    //     GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
    //     RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
    //             .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
    //             .serializeValuesWith(
    //                     RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer));
    //     return RedisCacheManager.builder().cacheWriter(cacheWriter).cacheDefaults(cacheConfiguration).build();
    // }
    
}
