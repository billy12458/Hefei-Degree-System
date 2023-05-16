package com.school.springboot.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.benmanes.caffeine.cache.Caffeine;

//貌似2.X版本的springboot对应的spring版本就删除了guava cache对应的内容，所以估计只有ehcache和caffeine可以用了
@Configuration
@SuppressWarnings("all")
public class CaffeineConfig {

    @Bean(value = "caffeine")
    public Caffeine getCaffeine() {
        Caffeine caffeine = Caffeine.newBuilder()
                .expireAfterAccess(15, TimeUnit.MINUTES)
                .initialCapacity(1024)
                .maximumSize(2048);
        return caffeine;
    }

    @Bean(value = "caffeineCacheManager")
    public CacheManager getCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(getCaffeine());
        caffeineCacheManager.setAllowNullValues(false);
        // cacheManager.setCacheNames(Arrays.asList("cache1"));
        return caffeineCacheManager;
    }

}