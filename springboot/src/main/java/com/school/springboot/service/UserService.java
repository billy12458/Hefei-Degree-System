package com.school.springboot.service;

import com.school.springboot.mapper.UserMapper;
import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import lombok.Data;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.springboot.VO.UserVO;
import com.school.springboot.dto.AuditDTO;
import com.school.springboot.dto.LoginDTO;
import com.school.springboot.entity.User;
import com.school.springboot.entity.shiro.MultipleToken;
import com.school.springboot.entity.tool.Result;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;

@Service(value = "userService")
@SuppressWarnings("all")
@Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "sql")
@Data
public class UserService {

    private List<String> tableList;

    private final UserMapper userMapper;

    private final RedisTemplate firstRedisTemplate;

    public Result login(String phone, String password) {
        User user = userMapper.selectByPhone(phone);
        if (!Objects.equals(user.getPassword(), password)) {
            return Result.fail("密码不匹配");
        }
        return Result.success("成功登录");
    }

    public void login(LoginDTO loginDTO, HttpServletRequest request,
            HttpServletResponse response) {
        // Subject subject = SecurityUtils.getSubject();
        // MultipleToken token = new MultipleToken(loginDTO.getPhone(),
        //         loginDTO.getPassword().toCharArray(), true, 1);
        // subject.login(token);
        User user = userMapper.getIdentityByPhone(loginDTO.getPhone());
        if(new Sha1Hash(loginDTO.getPassword(), user.getSalt(), 1024).toHex().equals(user.getPassword())) {
            StpUtil.login(user.getPhone(), loginDTO.isRememberMe());
        } else {
            throw new SaTokenException("登录失败！您的用户名或者密码有误");
        }
        
    }

    public void logoutProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Subject subject = SecurityUtils.getSubject();
        // if (!subject.isAuthenticated()) {
        //     subject.logout();
        //     return Result.fail("您未登录，请先登录！");
        // } else if (subject.isAuthenticated()) {
        //     firstRedisTemplate.opsForHash().delete("shiro:loginUsers", subject.getPrincipal().toString());
        //     subject.logout();
        //     return Result.success("您已成功退出！");
        // }
        // return null;
        if(!StpUtil.isLogin()) 
            throw new NotLoginException("您尚未登录，请先登录！", "1", "PC");
        StpUtil.logout();
    }

    public User getUserByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }

    public User getIdentityByPhone(String phone) {
        return userMapper.getIdentityByPhone(phone);
    }

    // @Cacheable(value = "userCache", key = "#phone")
    public PageInfo getUserInfoByPhone(String phone) {
        // return userMapper.getUserInfoByPhone(phone);
        PageInfo<User> pageInfo = PageHelper.startPage(1, 10)
              .doSelectPageInfo(() -> userMapper.getUserInfoByPhone(phone));
        return pageInfo;
    }

    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }

    public void updateUserStatus(AuditDTO auditDTO) {
        if (auditDTO.isStatus() == true) {
            userMapper.updateStatus(auditDTO.getPhone());
        }
    }

    public Result register(String userName, String phone, String password) {
        User user = new User();
        String salt = RandomStringUtils.randomAlphanumeric(8);
        user.setSalt(salt);
        user.setUsername(userName);
        user.setPassword(new Sha1Hash(password, salt, 1024).toHex());
        user.setIdentity(0);// 默认普通用户
        user.setPhone(phone);
        user.setStatus(0);// 未初始化
        userMapper.insUser(user);
        // 审核 管理员进行审核 异步处理 发给通道 如果管理员上线之后 就可以接收到信息 从而进行一个处理
        return Result.success("注册成功 请等待审核");
    }

    public List<User> findNotAuditUser() {
        List<User> users = userMapper.selectUserByStatus();
        return users;
    }

    public List<User> selectAllCrossTable() {
        return userMapper.selectAllCrossTable(tableList);
    }

    public List<User> getAllUserByWrapper(QueryWrapper<User> queryWrapper) {
        return userMapper.selectList(queryWrapper);
    }

    public Result audit(String phone) {
        User user = userMapper.selectByPhone(phone);
        userMapper.updateStatus(user.getPhone());
        return Result.success("审核成功！");
    }

}
