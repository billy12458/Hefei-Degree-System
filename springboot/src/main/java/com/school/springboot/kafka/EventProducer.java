// package com.school.springboot.kafka;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.stereotype.Component;

// @Component
// @SuppressWarnings("all")
// public class EventProducer {

//     @Autowired
//     private KafkaTemplate kafkaTemplate;
//     //处理事件
//     public void fireEvent(Event event){
//         //将事件发布到指定的主题
//         kafkaTemplate.send(event.getTopic(),event.getMsg());
//     }
    
// }