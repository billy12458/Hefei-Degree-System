// package com.school.springboot.config;

// import java.io.Serializable;
// import java.util.LinkedHashMap;
// import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
// import org.apache.shiro.cache.CacheManager;
// import org.apache.shiro.session.InvalidSessionException;
// import org.apache.shiro.session.Session;
// import org.apache.shiro.session.UnknownSessionException;
// import org.apache.shiro.session.mgt.SessionKey;
// import org.apache.shiro.session.mgt.SessionManager;
// import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
// import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
// // import org.apache.shiro.web.filter.InvalidRequestFilter;
// import org.apache.shiro.web.mgt.CookieRememberMeManager;
// import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
// import org.apache.shiro.web.servlet.SimpleCookie;
// import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
// import org.apache.shiro.web.session.mgt.WebSessionKey;
// import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.env.Environment;
// import com.school.springboot.entity.shiro.MultipleToken;
// import lombok.Data;

// /**
//  * The configuration class for shiro framework
//  * 
//  * @author 86136
//  * @author bill12458
//  * @version 0.2
//  */
// @SuppressWarnings("all")
// // configuration注解通过cglib代理保证这个类的bean是唯一的
// @Configuration
// @ConfigurationProperties(prefix = "org.shiro")
// @Data
// public class ShiroConfig {

//     private String hashAlgorithmName;

//     private String cookieName;

//     private String cipherKey;

//     private int hashIterations;

//     @Autowired
//     public Environment environment;

//     @Bean
//     public ShiroFilterFactoryBean getFactoryBean() {
//         ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
//         // 这里用hashmap也没啥事，不过linked更好
//         LinkedHashMap<String, String> filterMap = new LinkedHashMap<String, String>();
//         factoryBean.setLoginUrl("https://www.quickysharing.cn/login");
//         // factoryBean.setSuccessUrl("/success");
//         // 这样放行后就可以访问到静态资源了
//         // filterMap.put("/static/**", "anon");
//         // filterMap.put("/assets/css/**", "anon");
//         // filterMap.put("/assets/js/**", "anon");
//         // filterMap.put("/assets/img/**", "anon");
//         // filterMap.put("/assets/bootstrap/**", "anon");
//         // filterMap.put("/assets/fonts/**", "anon");
//         // filterMap.put("/login", "anon");
//         // filterMap.put("../static/assets/bootstrap/**", "anon");
//         // filterMap.put("/static/assets/bootstrap/**", "anon");
//         // filterMap.put("/register", "anon");
//         // filterMap.put("/user/getImage", "anon");
//         // filterMap.put("/user/search", "anon");
//         // filterMap.put("/register/getImage", "anon");
//         // filterMap.put("/files/search", "anon");
//         // filterMap.put("/files/tags/**", "anon");
//         // filterMap.put("/files/detail/**", "anon");
//         // filterMap.put("/files/random", "anon");
//         // filterMap.put("/test/findAll", "anon");
//         // filterMap.put("/test/ip", "anon");
//         // filterMap.put("/test/ipInfo", "anon");
//         // filterMap.put("/swagger-ui.html", "anon");
//         // filterMap.put("/swagger-ui/index.html", "anon");
//         // filterMap.put("/test/readme", "authc");
//         filterMap.put("/test/rsa/encrypt/**", "anon");
//         filterMap.put("/**", "anon");
//         factoryBean.setFilterChainDefinitionMap(filterMap);
//         factoryBean.setSecurityManager(getManager());
//         return factoryBean;
//     }

//     @Bean
//     public DefaultWebSecurityManager getManager() {
//         DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
//         // manager.setCacheManager(getCacheManager());
//         manager.setRememberMeManager(getRememberMe());
//         manager.setRealm(getCustomRealm());
//         return manager;
//     }

//     // @Bean
//     // public CacheManager getCacheManager() {
//     //     CacheManager cManager = null;
//     //     return cManager;
//     // }

//     /**
//      * The function for encrypting specific passwords
//      */
//     @Bean
//     public ShiroRealm getCustomRealm() {
//         ShiroRealm realm = new ShiroRealm();
//         AlipayHashCredentialsMatcher matcher = new AlipayHashCredentialsMatcher();
//         matcher.setHashAlgorithmName(hashAlgorithmName);
//         matcher.setHashIterations(hashIterations);
//         realm.setCredentialsMatcher(matcher);
//         realm.setAuthenticationTokenClass(MultipleToken.class);
//         return realm;
//     }

//     @Bean
//     public SimpleCookie getCookie() {
//         SimpleCookie simpleCookie = new SimpleCookie("school");
//         simpleCookie.setMaxAge(432000);
//         simpleCookie.setHttpOnly(true);
//         simpleCookie.setComment("cookie!");
//         simpleCookie.setSecure(false);
//         return simpleCookie;
//     }

//     /** The method responsible for summoning cookies and encrypting them */
//     @Bean
//     public CookieRememberMeManager getRememberMe() {
//         CookieRememberMeManager rememberMe = new CookieRememberMeManager();
//         rememberMe.setCookie(getCookie());
//         rememberMe.setCipherKey(environment.getProperty("org.shiro.cipherKey").getBytes());
//         return rememberMe;
//     }

//     @Bean
//     public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
//         DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//         advisorAutoProxyCreator.setProxyTargetClass(true);
//         return advisorAutoProxyCreator;
//     }

//     @Bean
//     public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
//         AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//         authorizationAttributeSourceAdvisor.setSecurityManager(getManager());
//         return authorizationAttributeSourceAdvisor;
//     }

//     // @Bean("invalidRequestFilter")
//     // public InvalidRequestFilter invalidRequestFilter() {
//     //     InvalidRequestFilter invalidRequestFilter = new InvalidRequestFilter();
//     //     invalidRequestFilter.setBlockNonAscii(false);
//     //     return invalidRequestFilter;
//     // }
// }
