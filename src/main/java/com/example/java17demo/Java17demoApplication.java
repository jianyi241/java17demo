package com.example.java17demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.example.java17demo.mapper")
@EnableAspectJAutoProxy(exposeProxy=true)
public class Java17demoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Java17demoApplication.class, args);
    }

}
