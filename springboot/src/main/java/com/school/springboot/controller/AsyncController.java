package com.school.springboot.controller;

import java.util.concurrent.ExecutionException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import com.school.springboot.service.AsyncTestService;
import com.school.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.school.springboot.annotation.ShiroLogin;
import com.school.springboot.entity.tool.Result;

@RestController
@Slf4j
@SuppressWarnings("all")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081", "http://192.168.10.6:8081" })
@RequestMapping(value = "/async")
@RequiredArgsConstructor
public class AsyncController {

    private final UserService userService;

    private final AsyncTestService asyncTestService;

    @ShiroLogin
    @RequiresRoles(value = "MOE")
    @RequestMapping(value = "/test", method = RequestMethod.PUT)
    public Result tesAsync() throws InterruptedException, ExecutionException {
        System.err.println("Mission dispatced!");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 15; i++) {
            userService.getAllUserByWrapper(null);
        }
        long end = System.currentTimeMillis() - start;
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 15; i++) {
            asyncTestService.getAsyncResultList().get();
        }
        long end1 = System.currentTimeMillis() - start1;
        return Result.success("Time spent:" + end + "ms\nTime spent:" + end1 + "ms");
    }
}
