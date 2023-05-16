// package com.school.springboot.config.quartz;

// import org.quartz.CronScheduleBuilder;
// import org.quartz.JobBuilder;
// import org.quartz.JobDetail;
// import org.quartz.Trigger;
// import org.quartz.TriggerBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class QuartzConfig {

//     // 这叫链式编程
//     @Bean(value = "meilisearchJobDetail")
//     public JobDetail getJobDetail() {
//         JobDetail jobDetail = JobBuilder.newJob(UserUpdateJob.class)
//                 .withIdentity("meilisearchJob", "meilisearchJob")
//                 // JobDataMap可以给任务execute传递参数
//                 .usingJobData("job_param", "job_param1")
//                 .storeDurably()
//                 .build();
//         return jobDetail;
//     }

//     @Bean(value = "testJobDetail")
//     public JobDetail getTestJobDetail() {
//         JobDetail jobDetail = JobBuilder.newJob(TestJob.class)
//                 .withIdentity("testJobDetail", "test")
//                 // JobDataMap可以给任务execute传递参数
//                 .usingJobData("job_param1", "job_param2")
//                 .storeDurably()
//                 .build();
//         return jobDetail;
//     }

//     @Bean(value = "meilisearchTrigger")
//     public Trigger getTrigger() {
//         Trigger trigger = TriggerBuilder.newTrigger()
//                 .forJob(getJobDetail())
//                 // .withIdentity("triggerEmp", "triggerEmp")
//                 .usingJobData("triggerEm_param", "triggerEmp_param1")
//                 .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))
//                 .build();
//         return trigger;
//     }

//     @Bean(value = "testTrigger")
//     public Trigger getTestTrigger() {
//         Trigger trigger = TriggerBuilder.newTrigger()
//                 .forJob(getTestJobDetail())
//                 // .withIdentity("triggerEmp", "triggerEmp")
//                 .usingJobData("triggerTestParam", "test param")
//                 .withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 * * * ?"))
//                 .startNow()
//                 .build();
//         return trigger;
//     }

// }
