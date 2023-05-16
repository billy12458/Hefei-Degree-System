package com.school.springboot.controller;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.school.springboot.annotation.ShiroLogin;
import com.school.springboot.entity.tool.Result;
import com.school.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@Slf4j
@SuppressWarnings("all")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:8082", "http://localhost:8080" })
public class LogoutController {

    private final UserService userService;
    
    // 没问题了，但有一点点无伤大雅的小问题
    @ShiroLogin
    @RequestMapping(value = "/logout", method = RequestMethod.PATCH)
    public Result logoutProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.logoutProcess(request, response);
        return Result.success("您已成功登出！"); 
    }

}
