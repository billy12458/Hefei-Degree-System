package com.school.springboot.service;

import java.io.IOException;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.school.springboot.config.AliPayProperties;
import com.school.springboot.entity.shiro.MultipleToken;
import com.school.springboot.utils.SaTokenUtils;

@Service(value = "alipayService")
@SuppressWarnings("all")
@SuppressAjWarnings
public class AliPayService {

    @Autowired
    private AliPayProperties aliPayProperties;

    @Autowired
    private AlipayClient alipayClient;

    public String loginCallBack(HttpServletRequest servletRequest, HttpServletResponse response1) throws IOException {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(servletRequest.getParameter("auth_code"));
        request.setGrantType("authorization_code");
        try {
            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
            // 调用接口获取用户信息
            AlipayUserInfoShareRequest request2 = new AlipayUserInfoShareRequest();
            String token = oauthTokenResponse.getAccessToken();
            System.out.println(token);
            AlipayUserInfoShareResponse response = alipayClient.execute(request2, token);
            if (response.isSuccess()) {
                Subject subject = SecurityUtils.getSubject();
                subject.login(new MultipleToken(response.getUserId(), new char['1'], true, 2));
                response1.sendRedirect("http://localhost:8080/school");

            } else {
                System.out.println("调用失败");
                System.out.println(response.getSubCode() + ":" + response.getSubMsg());
            }
        } catch (AlipayApiException e) {
            // 处理异常
            e.printStackTrace();
        }
        return "redirect:http://localhost:8080/school";
    }
}
