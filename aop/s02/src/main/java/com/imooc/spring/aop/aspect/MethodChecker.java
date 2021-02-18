package com.imooc.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MethodChecker {
    // ProceedingJoinPoint是JoinPoint的升级版
    public Object check(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            long startTime = new Date().getTime();
            // 返回同一个类的顶层方法调用返回值，即便是嵌套调用同一个类的其他方法，也是返回顶层方法返回值，时间累积到顶层方法
            // 如果顶层方法嵌套了不同类的方法，分别返回不同类的顶层方法返回值。
            // 这里顶层createUser方法调用不同类的方法userDao.insert()，proceedingJoinPoint.proceed()会返回这个insert方法返回值
            // insert方法返回值为  "insert()故意返回String字符串测试环绕通知"
            // 这里调用generateRandomPassword方法，但是是由顶层方法createUser方法去调用的，所以只看顶层方法createUser返回值，类型void，返回值为null
            // 而generateRandomPassword方法返回的字符串没有打印
            Object ret = proceedingJoinPoint.proceed();// 执行目标方法
            System.out.println(ret);
            long endTime = new Date().getTime();
            long duration = endTime - startTime;
            if (duration >= 1000) {
                String className = proceedingJoinPoint.getTarget().getClass().getName();
                String methodName = proceedingJoinPoint.getSignature().getName();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss SSS");
                String now = simpleDateFormat.format(new Date());
                System.out.println("=======" + now + ":" + className + "." + methodName + "(" + duration + "ms)=======");
            }
            return ret;
        } catch (Throwable throwable) {
            System.out.println("Exception message:" + throwable.getMessage());
            throw throwable;
        }
    }
}