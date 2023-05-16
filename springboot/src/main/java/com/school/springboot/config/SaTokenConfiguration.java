package com.school.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings(value = "unused")
@Configuration
public class SaTokenConfiguration implements WebMvcConfigurer {
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> urlList = new ArrayList<>();
        urlList.add("/login");
        urlList.add("/register");
        urlList.add("/user/getImage");
        urlList.add("/user/search");
        urlList.add("/district/all");
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handle -> {
            // 指定一条 match 规则
            SaRouter.notMatch(urlList)
                    .match("/**")
                    .check(r -> System.err.println());
        })).addPathPatterns("/**").excludePathPatterns(urlList);
    }
}

