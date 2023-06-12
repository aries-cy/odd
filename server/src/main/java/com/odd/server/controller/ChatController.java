package com.odd.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.odd.common.entity.Message;
import com.odd.common.util.WxUtil;
import com.odd.common.util.XmlUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {


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
        String fromUserName = message.getFromUserName();
        message.setFromUserName(message.getToUserName());
        message.setToUserName(fromUserName);
        String xml = XmlUtil.objectToXml(message);
        System.out.println(xml);
        return xml;
    }

}
