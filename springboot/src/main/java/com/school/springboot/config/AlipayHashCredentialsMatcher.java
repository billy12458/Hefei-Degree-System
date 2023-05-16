package com.school.springboot.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.context.annotation.Configuration;
import com.school.springboot.entity.shiro.MultipleToken;

@Configuration
public class AlipayHashCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo info) {
        MultipleToken token = (MultipleToken) authenticationToken;
        if(null != token.getPrincipal()){//这里返回true跳过密码验证
            return true;
        }
        return super.doCredentialsMatch(token, info);
    }

}