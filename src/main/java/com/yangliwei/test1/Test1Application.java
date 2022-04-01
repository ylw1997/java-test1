package com.yangliwei.test1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@SpringBootApplication
@MapperScan("com.yangliwei.test1.mapper")
@EnableScheduling
public class Test1Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Test1Application.class, args);
        System.out.println("hello world");
    }

}
