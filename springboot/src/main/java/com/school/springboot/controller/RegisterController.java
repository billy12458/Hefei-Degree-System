package com.school.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.school.springboot.VO.RegisterVO;
import com.school.springboot.entity.tool.Result;
import com.school.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@SuppressWarnings("all")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:8082", "http://localhost:8080", "http://www.quickysharing.cn", "https://www.quickysharing.cn" })
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private UserService userService;
    
    //注册
    // @RequestMapping(value = "/sure", method = RequestMethod.POST)
    // public Result register(@RequestBody @Validated RegisterVO registerVO){
    //     return userService.register(registerVO.getUserName(), registerVO.getPhone(), registerVO.getPassword());
    // }

}
