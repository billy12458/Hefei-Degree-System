package com.school.springboot.config;

import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncPoolConfig {
 
//     /**
//      *  核心线程数 （默认线程数）
//      */
//     @Value("${pool.core-size:4}")
// //    @Value("${pool.core-size:1}")
//     private int corePoolSize;
 
//     /**
//      *  最大线程数
//      */
//     @Value("${pool.max-size:8}")
// //    @Value("${pool.max-size:2}")
//     private int maxPoolSize;
 
//     /**
//      *  允许线程空闲时间 - 单位：秒
//      */
//     @Value("${pool.keep-alive:60}")
//     private int keepAliveSeconds;
 
//     /**
//      *  缓冲队列数
//      */
//     @Value("${pool.queue-capacity:5}")
//     private int queueCapacity;
 
//     /**
//      *  线程前缀名称
//      */
//     @Value("${thread-name-prefix: @Async-线程池pool}")
//     private String threadNamePrefix;
 
    //设置@Async的默认线程池
    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(64);//核心线程池数
        pool.setMaxPoolSize(300); // 最大线程数
        pool.setQueueCapacity(300);//队列容量,当核心线程数达到最大时，新任务会放在队列中排队等待执行
        pool.setKeepAliveSeconds(300);//线程空闲时间
        pool.setAllowCoreThreadTimeOut(false);//核心线程会一直存活，即使没有任务需要执行。（默认false）时，核心线程会超时关闭
        pool.setThreadNamePrefix("AsyncTest-");//线程前缀名称
        // 线程池的拒绝策略 --- 抛出异常 （默认方式）
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 初始化
        pool.initialize();
        return pool;
    }
}