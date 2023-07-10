package com.odd.server.kafka;

import com.odd.server.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = Constant.TOPIC, groupId = Constant.GROUP)
    public void consumeMessage(String message) {
        // 处理接收到的消息
        System.out.println("Received message: " + message);
    }

}
