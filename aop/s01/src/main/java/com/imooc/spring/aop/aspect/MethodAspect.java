package com.imooc.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;

import java.text.SimpleDateFormat;
import java.util.Date;

// 切面类
public class MethodAspect {
    // 切面方法，用于扩展额外功能
    // JoinPoint 连接点，通过连接点可以获取目标类/方法的信息
    public void printExecutionTime(JoinPoint joinPoint) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String now = simpleDateFormat.format(new Date());
        String className = joinPoint.getTarget().getClass().getName();// 获取目标类的名称
        String methodName = joinPoint.getSignature().getName(); // 获取目标方法名称
        System.out.println("----->" + now + ":" + className + "." + methodName);
    }
}
