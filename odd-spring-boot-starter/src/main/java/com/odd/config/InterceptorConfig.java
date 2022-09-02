package com.odd.config;

import com.odd.interceptor.ControllerInterceptor;
import com.odd.interceptor.ResponseInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class InterceptorConfig implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext context;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (context.containsBean("controllerMarker")) {
            registry.addInterceptor(new ControllerInterceptor());
        } else {
            registry.addInterceptor(new ResponseInterceptor());
        }


    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
