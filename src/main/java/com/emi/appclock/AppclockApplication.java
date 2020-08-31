package com.emi.appclock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.emi.appclock.mapper")
public class AppclockApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppclockApplication.class, args);
    }

}
