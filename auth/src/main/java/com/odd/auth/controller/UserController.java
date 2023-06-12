package com.odd.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.odd.auth.service.UserService;
import com.odd.common.controller.BaseController;
import com.odd.common.entity.Message;
import com.odd.common.entity.User;
import com.odd.common.util.WxUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Objects;


@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserService, User> {

    @GetMapping("test")
    public String test(HttpServletRequest request) {
        System.out.println("headers...");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + ": " + headerValue);
        }
        System.out.println("params...");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            System.out.println(paramName + ": " + paramValue);
        }
        return "test...";
    }

    @GetMapping("/wx")
    public String checkToken(String signature, String timestamp, String nonce, String echostr) {
        System.out.println("signature:" + signature + " timestamp:" + timestamp + " nonce:" + nonce + " echostr:" + echostr);
        if (signature != null && WxUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return "success";
    }

    @PostMapping(value = "/wx", produces = MediaType.APPLICATION_XML_VALUE)
    public String chat(@RequestBody Message message) {
        System.out.println(JSONObject.toJSONString(message));
        String xml = buildXmlResponse(message);
        System.out.println(xml);
        return xml;
    }


    private String buildXmlResponse(Message message) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[").append(message.getFromUserName()).append("]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[").append(message.getToUserName()).append("]]></FromUserName>");
        sb.append("<CreateTime>").append(System.currentTimeMillis() / 1000).append("</CreateTime>");
        sb.append("<MsgType><![CDATA[text]]></MsgType>");
        sb.append("<Content><![CDATA[").append(message.getContent()).append("]]></Content>");
        sb.append("</xml>");
        return sb.toString();
    }
}
