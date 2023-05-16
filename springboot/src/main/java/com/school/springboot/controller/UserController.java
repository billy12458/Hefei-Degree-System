package com.school.springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.school.springboot.dto.AuditDTO;
import com.school.springboot.entity.tool.Result;
import com.school.springboot.service.UserService;
import com.school.springboot.utils.SaTokenUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@SuppressWarnings("all")
@RequestMapping(value = "/user")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:8082", "http://localhost:8080" })
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public Result getUserInfo() {
        return Result.success(200, "查询成功！", userService.getUserInfoByPhone(SaTokenUtils.getCurrentUserName()));
    }

    @Deprecated
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public Result checkRole() {
        SecurityUtils.getSubject().hasRole("MOE");
        return Result.success("测试成功！");
    }

    // @RequiresRoles(value = "Super", logical = Logical.AND)
    @RequestMapping(value = "/unaudit", method = RequestMethod.GET)
    public Result getUnauditedUsers() {
        return Result.success(userService.findNotAuditUser());
    }

    // @RequiresGuest
    @ResponseBody
    @RequestMapping(value = "/crossTable", method = RequestMethod.GET)
    public Result getAllCrossTable() {
        return Result.success(userService.selectAllCrossTable());
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Result getCurrentUserName() {
        return Result.success(SaTokenUtils.getCurrentUserName());
    }

    @RequestMapping(value = "/getidentity", method = RequestMethod.GET)
    public Result getUserRoles() {
        return Result.success(userService.getIdentityByPhone(SaTokenUtils.getCurrentUserName()));
    }

    @RequestMapping(value = "/audit", method = RequestMethod.PUT)
    public Result auditSpecificUser(@RequestBody AuditDTO auditDTO) {
        userService.updateUserStatus(auditDTO);
        return Result.success("审核"+ auditDTO.getUserName()+"完成");
    }
}