package com.odd.advice;

import com.alibaba.fastjson.JSON;
import com.odd.annotation.ResponseResult;
import com.odd.model.RestResult;
import com.odd.properties.ResultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ControllerResultAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private ResultProperties resultProperties;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            RestController responseResult = (RestController) request.getAttribute(RestController.class.toString());
            return responseResult != null;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof ResponseResult) {
            return o;
        } else if (o instanceof String) {
            return JSON.toJSONString(new RestResult<>(resultProperties.getCode(), resultProperties.getMsg(), o));
        }
        return new RestResult<>(resultProperties.getCode(), resultProperties.getMsg(), o);
    }
}
