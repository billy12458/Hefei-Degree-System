package com.school.springboot.controller;

import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.codec.DecoderException;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.school.springboot.annotation.ShiroLogin;
import com.school.springboot.entity.tool.Result;
import com.school.springboot.utils.RSAUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/test")
@SuppressWarnings("all")
@RequiredArgsConstructor
public class TestController {

    public final RSAUtils rsaUtils;
    
    @ShiroLogin
    @ResponseBody
    @RequestMapping(value = "/rsa/encrypt/{origin}", method = RequestMethod.GET)
    public Result getRSATestResult(@PathVariable(value = "origin") String originalString) {
        return Result.success(rsaUtils.getEncryptedContent(originalString));
    }

    @ShiroLogin
    @ResponseBody
    @RequiresGuest
    @RequestMapping(value = "/rsa/decrypt", method = RequestMethod.GET)
    public Result getRSADecryptResult() throws DecoderException {
        return Result.success(rsaUtils.getDecryptedPassword(rsaUtils.getEncryptedContent("LebronJames23 from cleveland,Ohio welcome!!!")));
    }

    @ShiroLogin
    @ResponseBody
    @RequiresGuest
    @RequestMapping(value = "/assert", method = RequestMethod.GET)
    public Result getAssertResult() throws DecoderException {
        Assert.isTrue(true, ApiErrorCode.FAILED);
        return Result.success("hahaha");
    }

}
