package com.school.springboot.utils;

import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.session.SaSession;
import lombok.extern.slf4j.Slf4j;

// @Profile(value = "dev")
@Slf4j
@Component
@SuppressWarnings("all")
@SuppressAjWarnings
public class SaTokenUtils {

    public static String getUserName() {
        // return SecurityUtils.getSubject().getPrincipals().toString();
        // return SecurityUtils.getSubject().getSession().getAttribute("userName").toString();
        // return SecurityUtils.getSubject().getSession().getAttribute(DefaultSubjectContext.class.getName() + "_PRINCIPALS_SESSION_KEY").toString();
        return StpUtil.getLoginIdAsString();
    }

    public static String getCurrentUserName() {
        return StpUtil.getLoginIdAsString();
        // return SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal().toString();
    }

    public static boolean hasRole(String role) {
        // return SecurityUtils.getSubject().hasRole(role);
        return false;
    }

    public static List<String> getRoles() {
        // return SecurityUtils.getSubject().
        return null;
    }

    public SaSession getCurrentSession() {
        // return SecurityUtils.getSubject().getSession();
        return StpUtil.getSessionByLoginId(StpUtil.getLoginId());
    }
}
