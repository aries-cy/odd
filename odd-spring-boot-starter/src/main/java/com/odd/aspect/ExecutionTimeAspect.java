package com.odd.aspect;

import com.alibaba.fastjson.JSONObject;
import com.odd.annotation.ExecuteLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Slf4j
@Aspect
public class ExecutionTimeAspect {

    @Around("@annotation(com.odd.annotation.ExecuteLog)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Class<?> targetClass = joinPoint.getTarget().getClass();
        Method method = targetClass.getDeclaredMethod(methodName, getParameterTypes(joinPoint));
        // 获取方法上的@ExecuteLog注解
        ExecuteLog executeLog = method.getAnnotation(ExecuteLog.class);
        // 打印执行时间
        if (executeLog.executeTime()) {
            log.info("{}.{}() execute time is: {} ms", className, methodName, endTime - startTime);
        }
        // 打印参数
        if (executeLog.param()) {
            printParam(joinPoint.getArgs(), className, methodName);
        }
        // 打印返回结果
        if (executeLog.result()) {
            log.info("{}.{}() result is : {}", className, methodName, JSONObject.toJSONString(result));
        }
        return result;
    }

    private void printParam(Object[] args, String className, String methodName){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append("param").append(i).append(":").append(JSONObject.toJSONString(args[i]));
            if (i < args.length -1 ) {
                sb.append("; ");
            }
        }
        String params = sb.toString();
        if (params.length() > 0) {
            log.info("{}.{}() params is : {}", className, methodName, params);
        }
    }


    private Class<?>[] getParameterTypes(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getParameterTypes();
    }

}
