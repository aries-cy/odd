package com.odd.interceptor;

import com.odd.annotation.ResponseResult;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class ResponseInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> clazz = ((HandlerMethod) handler).getBeanType();
            Method method = handlerMethod.getMethod();
            ResponseResult classAnnotation = AnnotationUtils.findAnnotation(clazz, ResponseResult.class);
            ResponseResult methodAnnotation = AnnotationUtils.findAnnotation(method, ResponseResult.class);
            if(methodAnnotation != null ){
                request.setAttribute(ResponseResult.class.toString(),methodAnnotation);
            }else if(classAnnotation != null){
                request.setAttribute(ResponseResult.class.toString(),classAnnotation);
            }
        }
        return true;
    }
}
