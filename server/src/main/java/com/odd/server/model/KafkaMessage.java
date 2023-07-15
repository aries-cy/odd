package com.odd.server.model;

import lombok.Data;

@Data
public class KafkaMessage{


    private Long id;

    private String name;

    private String address;

    public KafkaMessage(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

}
