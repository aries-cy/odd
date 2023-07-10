package com.odd.server.kafka;

import com.odd.server.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Slf4j
@Component
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;


    public void sendMessage(String message) {
        kafkaTemplate.send(Constant.TOPIC, message);
    }
}
