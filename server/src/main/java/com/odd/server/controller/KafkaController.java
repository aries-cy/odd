package com.odd.server.controller;

import com.odd.server.kafka.KafkaProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class KafkaController {

    @Resource
    private KafkaProducer producer;

    @GetMapping("/kafka/produce")
    public void produce(@RequestParam String msg){
        producer.sendMessage(msg);
    }


}
