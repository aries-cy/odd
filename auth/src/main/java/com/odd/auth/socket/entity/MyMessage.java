package com.odd.auth.socket.entity;

import com.alibaba.fastjson.JSONObject;
import com.odd.common.entity.User;
import lombok.Data;

@Data
public class MyMessage {

    private User sender;

    private User receiver;

    private String content;
}
