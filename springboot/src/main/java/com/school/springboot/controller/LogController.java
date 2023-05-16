package com.school.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.school.springboot.annotation.ShiroLogin;
import com.school.springboot.entity.Log;
import com.school.springboot.entity.tool.Result;
import com.school.springboot.mapper.LogMapper;
import com.school.springboot.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author shijiu
 */
@RestController
@CrossOrigin(value = { "http://localhost:8080", "http://localhost:8081" })
@Slf4j
@SuppressWarnings("all")
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private PageUtils pageUtils;

    @RequiresUser
    @ShiroLogin
    @RequiresRoles(value = { "school", "MOE" }, logical = Logical.OR)
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Result getLog(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, InstantiationException {
        PageInfo logInfo = pageUtils.getLogsInfo("getAllLogs", pageNum, 10);
        return Result.success(200, "查询分页数据成功！", logInfo);
    }

}
