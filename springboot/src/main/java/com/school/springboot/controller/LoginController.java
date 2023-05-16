package com.school.springboot.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.school.springboot.annotation.ShiroLogin;
import com.school.springboot.dto.LoginDTO;
import com.school.springboot.entity.shiro.MultipleToken;
import com.school.springboot.entity.tool.Result;
import com.school.springboot.service.AliPayService;
import com.school.springboot.service.UserService;
import com.school.springboot.utils.GifUtils;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@SuppressWarnings("all")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:8082", "http://localhost:8080",
        "http://www.hefeidegree.cn" })
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AliPayService aliPayService;

    // 没问题了
    @RequestMapping(value = "/user/getImage", method = RequestMethod.GET)
    public void getImage(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws IOException {
        GifUtils.getImage(request, response, session, "loginCode");
    }

    @ShiroLogin
    // 没问题了
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result loginResult(@RequestBody @Validated LoginDTO loginDTO, HttpServletRequest request,
            HttpServletResponse response) throws NoSuchAlgorithmException, IOException {
        userService.login(loginDTO, request, response);
        return Result.success(200, "登录成功", "登录成功");
    }

    @ShiroLogin
    @RequestMapping(value = "/login/aliPay", method = RequestMethod.GET)
    public Result loginWithAlipay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        aliPayService.loginCallBack(request, response);
        return Result.success(200, "测试成功！", "测试成功！");
    }
}
