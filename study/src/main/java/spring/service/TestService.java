package spring.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TestService {

    public TestService(){
        System.out.println("1.testService 构造...");
    }

    @PostConstruct
    public void init(){
        System.out.println("3.testService PostConstruct init...");
    }

    public void test(){
        System.out.println("4.test success...");
    }

}
