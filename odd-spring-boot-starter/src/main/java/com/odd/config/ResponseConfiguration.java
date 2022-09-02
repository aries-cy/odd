package com.odd.config;

import com.odd.advice.ControllerResultAdvice;
import com.odd.advice.ResponseResultAdvice;
import com.odd.model.ControllerMarker;
import com.odd.properties.ResultProperties;
import com.odd.service.ResponseService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(ResultProperties.class)
public class ResponseConfiguration  {

    @Bean
    @ConditionalOnMissingBean(ControllerMarker.class)
    ResponseResultAdvice responseResultAdvice(){
        return new ResponseResultAdvice();
    }


    @Bean
    @ConditionalOnBean(ControllerMarker.class)
    ControllerResultAdvice controllerResultAdvice(){
        return new ControllerResultAdvice();
    }

    @Bean
    ResponseService interceptorConfig(){
        return new ResponseService();
    }

}
