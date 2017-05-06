package com.beardream.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by laxzh on 2017/5/6.
 */
@Aspect
@Component
public class LogAspect {

    private final static Logger logger = LoggerFactory.getLogger("LogAspect.class");

    @Pointcut("execution(* com.beardream.Controller..*(..)) && @annotation(com.beardream.ioc.Log)")
    public void log(){
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("before log");
        //获取请求的各种信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("ok={}","日志拦截器已经进来了");
//        logger.info("url={}",request.getRequestURL());
//
//        logger.info("method={}",request.getMethod());
//
//        logger.info("ip={}",request.getRemoteAddr());
//
//        //类方法
//        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
//
//        //参数
//        logger.info("args={}",joinPoint.getArgs());
//
//        //拦截确保参数正确
//        Map<String,String[]> paras = request.getParameterMap();
//        logger.info("param={}",paras.get("username"));
    }

    @After("log()")
    public void doAfter(){
        logger.info("日志拦截器 after");
    }

//    @AfterReturning(returning = "object",pointcut = "log()")
//    public void doAfterrReturning(){
//        logger.info();
//    }
}
