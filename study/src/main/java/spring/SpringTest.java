package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.AppConfig;
import spring.service.TestService;

public class SpringTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TestService testService = (TestService) context.getBean("testService");
        testService.test();
    }
}
