// package com.school.springboot.kafka;

// import com.school.springboot.entity.Log;
// import com.school.springboot.mapper.LogMapper;
// import org.apache.kafka.clients.consumer.ConsumerRecord;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.kafka.annotation.KafkaListener;
// import org.springframework.stereotype.Component;
// import java.io.BufferedWriter;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.OutputStreamWriter;

// @Component
// public class EventConsumer  {

//     @Autowired
//     private LogMapper logMapper;

//     private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);


//     @KafkaListener(topics = {"log"})
//     public void handleMessage(ConsumerRecord record) throws IOException {
//         if(record==null||record.value()==null){
//             logger.error("消息的内容为空");
//             return;
//         }
//         //发送站内通知
//         String path = "C:/java5/springboot-log.txt";
//         BufferedWriter out = new BufferedWriter(
//                 new OutputStreamWriter(new FileOutputStream(path,true)));
//         Object value = record.value();
//         System.out.println(value);
//         out.write(value.toString()+"\r\n");
//         out.close();
//     }


// }