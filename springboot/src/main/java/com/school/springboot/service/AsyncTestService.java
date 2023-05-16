package com.school.springboot.service;

import java.util.List;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import com.school.springboot.entity.User;
import com.school.springboot.mapper.UserMapper;


@SuppressWarnings("all")
@Service(value = "asyncTestService")
public class AsyncTestService {
    
    @Autowired
    private UserMapper userMapper;

    @Async(value = "taskExecutor")
    public Future<List<User>> getAsyncResultList() {
        System.err.println(Thread.currentThread().getName()+"start---");
        List<User> list = userMapper.selectList(null);
        return new AsyncResult<List<User>>(list);
    }

}
