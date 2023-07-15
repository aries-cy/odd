package com.odd.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.odd.server.model.KafkaMessage;
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
    private KafkaTemplate<String, Object> kafkaTemplate;

    @RequestMapping("message/send")
    public String send(@RequestParam String msg) {
        // 一个主题有多个分区
        // 1.默认情况下，消息会根据分区策略选择分区，而kafka默认的分区策略是轮询策略
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("demo", msg + "0");
        // 2.可以根据业务类型，将消息发送到指定的key，kafka会根据key的hash值来确定消息发送到哪个分区
        kafkaTemplate.send("demo", "key", msg + "1");
        kafkaTemplate.send("demo", "msg", JSONObject.toJSONString(new KafkaMessage(1L, "小明", "成都市")));

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info(">>>send message failed...");
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info(">>>send message success...");
            }
        });
        return "success";
    }

    /**
     * 1. 一个分区的消息只能被同一个消费组里的一个消费者消费
     * 2. 一个分区的消息可以被不同消费组里的消费者消费
     * 3. 如果消费者端不提交偏移量，kafka会认为改消息还没有被消费，重启或分区再均衡时，将消息重新分配给其他消费者，即导致消息被重复消费
     */
    @KafkaListener(topics = "demo", groupId = "test1")
    public void consumer1(ConsumerRecord<?, ?> record) {
        log.info(">>>Consumer1消费消息，GroupId is : test1, topic is:{}, offset is:{}, value is:{}", record.topic(), record.offset(), record.value());
    }

    @KafkaListener(topics = "demo", groupId = "test1")
    public void consumer2(ConsumerRecord<?, ?> record) {
        log.info(">>Consumer2消费消息，GroupId is : test1, topic is:{}, offset is:{}, value is:{}", record.topic(), record.offset(), record.value());
    }

    @KafkaListener(topics = "demo", groupId = "test2")
    public void consumer3(ConsumerRecord<?, ?> record) {
        log.info(">>Consumer3消费消息，Key is : {}, topic is:{}, offset is:{}, value is:{}", record.key(), record.topic(), record.offset(), record.value());
    }

}
