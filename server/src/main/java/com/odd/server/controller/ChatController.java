package com.odd.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.odd.common.entity.Message;
import com.odd.common.util.WxUtil;
import com.odd.common.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ChatController {


    @GetMapping("/wx")
    public String checkToken(String signature, String timestamp, String nonce, String echostr) {
        log.info("signature:{}, timestamp:{}, nonce:{}, echostr:{}", signature, timestamp, nonce, echostr);
        if (signature != null && WxUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return "success";
    }

    @PostMapping(value = "/wx", produces = MediaType.APPLICATION_XML_VALUE)
    public String chat(@RequestBody Message message) {
        log.info("user message is : {}", JSONObject.toJSONString(message));
        String fromUserName = message.getFromUserName();
        message.setFromUserName(message.getToUserName());
        message.setToUserName(fromUserName);
        String xml = XmlUtil.objectToXml(message);
        log.info("to user message is :{}", xml);
        return xml;
    }

}
