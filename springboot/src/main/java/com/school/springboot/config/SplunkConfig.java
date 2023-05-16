// package com.school.springboot.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import com.splunk.Args;
// import com.splunk.HttpService;
// import com.splunk.Job;
// import com.splunk.SSLSecurityProtocol;
// import com.splunk.Service;
// import com.splunk.ServiceArgs;

// @Configuration
// @SuppressWarnings("all")
// public class SplunkConfig {

//     // 先写死
//     @Bean(value = "allSearchJob")
//     public Service getSplunkService() {
//         Service service = null;
//         HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_1);
//         ServiceArgs loginArgs = new ServiceArgs();
//         loginArgs.setPort(8089);
//         loginArgs.setHost("localhost");
//         loginArgs.setScheme("https");
//         loginArgs.setUsername("admin"); // Use your username
//         loginArgs.setPassword("mongo2023java27897"); // Use your password
//         // Initialize the SDK client
//         service = Service.connect(loginArgs);
//         // Job job = service.search("search index=user phone=18256879425 | fields all");
//         // while (!job.isReady()) {
//         //     try {
//         //         // Thread.sleep(1); // 500 ms
//         //     } catch (Exception e) {
//         //         e.printStackTrace();
//         //     }
//         // }
//         // return job;
//         return service;
//     }
// }
