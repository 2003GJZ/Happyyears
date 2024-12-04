package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.system.mapper") // 扫描Mapper接口
public class InnovateItApplication {

    public static void main(String[] args) {
        SpringApplication.run(InnovateItApplication.class, args);
    }

}
