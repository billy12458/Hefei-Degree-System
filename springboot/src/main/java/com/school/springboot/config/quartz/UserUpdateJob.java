package com.school.springboot.config.quartz;

import java.util.List;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import com.school.springboot.entity.User;
import com.school.springboot.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
// import org.springframework.scheduling.quartz.LocalDataSourceJobStore;

// @RequiredArgsConstructor
@DisallowConcurrentExecution
@SuppressWarnings("all")
@SuppressAjWarnings
public class UserUpdateJob implements Job {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Index userIndex;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            // List<User> userList = userMapper.selectList(null);
            // System.err.println("Current Time:" + new SimpleDateFormat("yyyy-MM-dd
            // HH:mm:ss").format(new Date()));
            // String userString = JSONObject.toJSONString(userList,
            // JSONWriter.Feature.NotWriteEmptyArray);
            Path fileName = Path.of("C:/Users/86136/Desktop/user.json");
            String moviesJson = Files.readString(fileName);
            Client client = new Client(new Config("http://localhost:7700", "dvLUjOaAXTgSaqyqGWin1JeVCgwuBqotxhOlINlEI18"));
            Index index = client.index("userTest");
            index.addDocuments(moviesJson);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
