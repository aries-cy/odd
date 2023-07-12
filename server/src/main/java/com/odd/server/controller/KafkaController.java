package com.odd.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class KafkaController {

    @Resource
    private KafkaTemplate<String,Object> kafkaTemplate;

    @RequestMapping("message/send")
    public String send(@RequestParam String msg) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("demo", msg);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("send message failed...");
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("send message success...");
            }
        });
        return "success";
    }

    @KafkaListener(topics = "demo")
    public void listen(ConsumerRecord<?, ?> record) {
        log.info("topic is:{}, offset is:{}, value is:{}", record.topic(), record.offset(), record.value());
    }

}
