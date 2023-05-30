package com.odd.auth.socket.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServerHandler implements WebSocketHandler {

    private static final String USER_ID = "user_id";
    private static final String CONVERSATION_ID = "conversation_id";


    // 存储群聊的会话
    private static final Map<String, Map<String, WebSocketSession>> chatMap = new ConcurrentHashMap<>();

    //TODO 1.可以在这里定义一个map，存储坐席的session，当坐席上线后，将坐席的session存储在该map中

    // 建立连接后执行的逻辑
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 在websocket的header里添加conversation_id当做会话ID,user_id作为userId
        String userId = session.getHandshakeHeaders().getFirst(USER_ID);
        String conversationId = session.getHandshakeHeaders().getFirst(CONVERSATION_ID);
        if (chatMap.get(conversationId) == null) {
            Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
            sessionMap.put(userId, session);
            chatMap.put(conversationId, sessionMap);
        } else {
            Map<String, WebSocketSession> sessionMap = chatMap.get(conversationId);
            sessionMap.put(userId, session);
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        HttpHeaders headers = session.getHandshakeHeaders();
        // 解析消息，获取发送者、接收者以及消息内容
        broadcastMessage(headers.getFirst(USER_ID), headers.getFirst(CONVERSATION_ID), message.getPayload().toString());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        // 处理传输错误
        System.out.println("error");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        HttpHeaders headers = session.getHandshakeHeaders();
        Map<String, WebSocketSession> sessionMap = chatMap.get(headers.getFirst(CONVERSATION_ID));
        sessionMap.remove(headers.getFirst(USER_ID));
        if (sessionMap.size() == 0) {
            System.out.println("会话结束");
            chatMap.remove(headers.getFirst(CONVERSATION_ID));
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    // 群发消息
    private void broadcastMessage(String senderId, String conversationId, String content) throws IOException {
        // TODO 4.这里也可以做排队的逻辑，每个坐席接待一个客户都可以放入一个队列，判断队列的大小，实现排队功能
        //TODO 3.当客户转人工是，这里可以根据客户所选择的技能组，去选择坐席。将坐席的session加入到chatMap中
        Map<String, WebSocketSession> sessionMap = chatMap.get(conversationId);
        for (Map.Entry<String, WebSocketSession> entry : sessionMap.entrySet()) {
            String userId = entry.getKey();
            WebSocketSession session = entry.getValue();
            if (!senderId.equals(userId)) {
                session.sendMessage(new TextMessage("Received message from " + senderId + ": " + content));
                //TODO 4.这里可以将发送的消息持久化
            }
        }
    }

}
