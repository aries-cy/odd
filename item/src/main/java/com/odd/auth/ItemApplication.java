package com.odd.auth;

import com.odd.annotation.EnableResponseResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableResponseResult
@EnableAspectJAutoProxy
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }
}
