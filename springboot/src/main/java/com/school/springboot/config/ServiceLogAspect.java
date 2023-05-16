// package com.school.springboot.config;

// import com.baomidou.mybatisplus.core.toolkit.Assert;
// import com.school.springboot.entity.Log;
// import com.school.springboot.entity.LoginUser;
// import com.school.springboot.entity.User;
// // import com.school.springboot.kafka.Event;
// // import com.school.springboot.kafka.EventProducer;
// import com.school.springboot.mapper.LogMapper;
// import com.school.springboot.mapper.UserMapper;
// // import com.school.springboot.utils.HostHolder;
// import com.school.springboot.utils.LogUtils;
// import com.school.springboot.utils.SaTokenUtils;
// import lombok.SneakyThrows;
// import org.apache.shiro.SecurityUtils;
// import org.aspectj.lang.JoinPoint;
// import org.aspectj.lang.annotation.After;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Before;
// import org.aspectj.lang.annotation.Pointcut;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import org.springframework.web.context.request.RequestContextHolder;
// import org.springframework.web.context.request.ServletRequestAttributes;
// import jakarta.servlet.http.HttpServletRequest;
// import java.io.IOException;
// import java.text.SimpleDateFormat;
// import java.util.Date;

// @Component
// @Aspect
// @SuppressWarnings("all")
// public class ServiceLogAspect {

//     // @Autowired
//     // private HostHolder hostHolder;

//     // @Autowired
//     // private EventProducer eventProducer;
    
//     @Autowired
//     private UserMapper userMapper;

//     @Autowired
//     private LogMapper logMapper;

//     private static final Logger logger =  LoggerFactory.getLogger(ServiceLogAspect.class);
    
//     @Pointcut("execution(* com.school.springboot.controller.*.*(..)) && !@annotation(com.school.springboot.annotation.ShiroLogin)")
//     public void pointcut(){

//     }
    
//     @SneakyThrows
//     @Before("pointcut()")
//     public void before(JoinPoint joinPoint) throws IOException {
//         //用户某某某 在 什么时间 访问了什么功能
//         ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//         HttpServletRequest request = attributes.getRequest();
//         String ip = request.getRemoteHost();
//         // Assert.notNull(SaTokenUtils.getCurrentUserName(), "您尚未登录！", null);
//         User user = userMapper.selectByPhone(SaTokenUtils.getCurrentUserName());
// //        String username = user.getUsername();
//         String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//         String target = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
//         String log = null;
//         if(user==null){
//             log = String.format("ip地址为[%s],在[%s],访问了[%s]接口.",  ip, now, target);
//         }else{
//             log = String.format("用户[%s],ip地址为[%s],在[%s],访问了[%s]接口.", user.getUsername(), ip, now, target);
//         }
//         logger.info(log);

//         // Event event = new Event();
//         // event.setTopic("log");
//         // event.setMsg(log);
//         // eventProducer.fireEvent(event);
//     }


//     @After("pointcut()")
//     public void after(JoinPoint joinPoint){
//         Log log = new Log();
// //        User user = hostHolder.getUser();
//         String phone = SecurityUtils.getSubject().getPrincipal().toString();
//         System.out.println("phone====="+phone);
//         User user = userMapper.selectByPhone(phone);
//         System.out.println("user====="+user);
//         if(user==null){
//             log.setPhone("普通用户");
//         }
//         else{
//             log.setPhone(phone);
//         }
//         String target = joinPoint.getSignature().getName();
//         System.out.println("target====="+target);
//         String method = LogUtils.getMethod(target);
//         log.setTime(new Date());
//         log.setEvent(method);
//         System.out.println("log========"+log);
//         new Thread(new Runnable() {
//             @Override
//             public void run() {
//                 logMapper.insLog(log);
//             }
//         }).start();
//     }
// }