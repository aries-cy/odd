package com.odd.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "response.result"
)
@Data
public class ResultProperties {


    private String code;

    private String msg;

}
