package com.odd.interceptor;

import com.odd.annotation.ResponseResult;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class ControllerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            Class<?> clazz = ((HandlerMethod) handler).getBeanType();
            RestController classAnnotation = AnnotationUtils.findAnnotation(clazz, RestController.class);
            if(classAnnotation != null){
                request.setAttribute(RestController.class.toString(),classAnnotation);
            }
        }
        return true;
    }
}
