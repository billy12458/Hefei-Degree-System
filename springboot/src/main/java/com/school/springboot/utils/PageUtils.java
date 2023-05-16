package com.school.springboot.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.springboot.entity.Log;
import com.school.springboot.service.LogService;

//需要在这里调用missionMapper的方法
@Component
@SuppressWarnings("all")
public class PageUtils {

    @Autowired
    public LogService logService;

    // public static PageInfo<Employee> getEmpInfo(int pageNum, int pageSize,
    //         boolean count,
    //         List<Employee> list) {
    //     PageHelper.startPage(pageNum, pageSize, count);
    //     PageInfo<Employee> infoPage = new PageInfo<>(list);
    //     return infoPage;
    // }

    public PageInfo<Log> getLogsInfo(String methodName, int pageNum, int pageSize)
            throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, InstantiationException {
        Method lisMethod = LogService.class.getMethod(methodName);
        PageHelper.startPage(pageNum, pageSize, true);
        List<Log> logs = (List<Log>) lisMethod.invoke(logService);
        PageInfo<Log> infoPage = new PageInfo<>(logs);
        return infoPage;
    }

}
