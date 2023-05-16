// package com.school.springboot.config;

// import org.apache.commons.lang3.StringUtils;
// import org.apache.shiro.SecurityUtils;
// import org.apache.shiro.authc.AuthenticationException;
// import org.apache.shiro.authc.AuthenticationInfo;
// import org.apache.shiro.authc.SimpleAuthenticationInfo;
// import org.apache.shiro.authc.AuthenticationToken;
// import org.apache.shiro.authc.UsernamePasswordToken;
// import org.apache.shiro.authz.AuthorizationInfo;
// import org.apache.shiro.authz.SimpleAuthorizationInfo;
// import org.apache.shiro.realm.AuthorizingRealm;
// import org.apache.shiro.subject.PrincipalCollection;
// import org.apache.shiro.util.ByteSource;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.util.ObjectUtils;
// import com.school.springboot.entity.User;
// import com.school.springboot.entity.shiro.MultipleToken;
// import com.school.springboot.service.UserService;

// @SuppressWarnings("all")
// public class ShiroRealm extends AuthorizingRealm {

//     @Autowired
//     private UserService userService;

//     @Override
//     protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//         // 那么这里userName就改成phone就可以了，012对应三种用户
//         String userName = principals.getPrimaryPrincipal().toString();
//         User user = userService.getUserByPhone(userName);
//         SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//         if (!ObjectUtils.isEmpty(user) && 0 == user.getIdentity()) {
//             info.addRole("public");
//         } if (!ObjectUtils.isEmpty(user) && 1 == user.getIdentity()) {
//             info.addRole("school");
//         } if (!ObjectUtils.isEmpty(user) && 2 == user.getIdentity()) {
//             info.addRole("MOE");
//         }
//         return info;
//     }

//     @Override
//     protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
//         MultipleToken token = (MultipleToken) arg0;
//         String userName = token.getPrincipal().toString();
//         if(token.getLoginType() == 1) {
//             User user = userService.getUserByPhone(userName);
//             if (!ObjectUtils.isEmpty(user)) {
//                 return new SimpleAuthenticationInfo(user.getPhone(), user.getPassword(),
//                         ByteSource.Util.bytes(user.getSalt()), this.getName());
//             }
//             return null;
//         }else {
//             User user = userService.getUserByUserName(userName);
//             if (!ObjectUtils.isEmpty(user)) {
//                 return new SimpleAuthenticationInfo(user.getPhone(), "123456",
//                         ByteSource.Util.bytes(user.getSalt()), this.getName());
//             }
//             return null;
//         }
//     }
// }