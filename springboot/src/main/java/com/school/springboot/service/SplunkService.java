package com.school.springboot.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONReader;
import com.school.springboot.entity.User;
// import com.splunk.Args;
// import com.splunk.Event;
// import com.splunk.Job;
// import com.splunk.ResultsReader;
// import com.splunk.ResultsReaderJson;
// import com.splunk.ServiceArgs;
import lombok.RequiredArgsConstructor;

@Service(value = "splunkTestService")
@SuppressWarnings("all")
// @RequiredArgsConstructor
public class SplunkService {

    // @Autowired
    // private Job allSearchJob;

    // @Autowired
    // private com.splunk.Service allSearchJob;

    public List<User> testSplunkSearch() throws IOException {
        // Job job = allSearchJob.search("search index=user phone=18256879425 | fields all");
        // while (!job.isReady()) {
        //     try {
        //         // Thread.sleep(1); // 500 ms
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        // }
        // List<User> list = new ArrayList<>();
        // Args outputArgs = new Args();
        // outputArgs.put("output_mode", "json");
        // ResultsReader reader = new ResultsReaderJson(job.getEvents(outputArgs));
        // for (Event event : reader) {
        //     User user = (User) JSONObject.parseObject(event.get("_raw"), User.class);
        //     list.add(user);
        // }
        // return list;
        return null;
    }
}
