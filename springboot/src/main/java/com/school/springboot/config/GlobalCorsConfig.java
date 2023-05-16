package com.school.springboot.config;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SuppressAjWarnings
@SuppressWarnings("all")
@Configuration
public class GlobalCorsConfig {

    @Bean
    public FilterRegistrationBean corsFilter() {
        // 1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        // 1) 允许的域,不要写*，否则cookie就无法使用了
        config.addAllowedOrigin("http://localhost:8081");
        config.addAllowedOrigin("http://localhost:8082");
        config.addAllowedOrigin("http://localhost:8080");
        config.addAllowedOrigin("http://192.168.10.6:8080");
        config.addAllowedOrigin("http://192.168.10.6:8081");
        // 2) 是否发送Cookie信息
        config.setAllowCredentials(true);
        // 3) 允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        config.setMaxAge(10800L);
        // 4）允许的头信息
        config.addAllowedHeader("*");
        // 2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        // 3.返回新的CorsFilter.
        // return new CorsFilter(configSource);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(configSource));
        bean.setOrder(0);
        return bean;
    }

}
