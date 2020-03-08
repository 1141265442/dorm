package com.zxhan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.zxhan.modular.mapper")
public class DormApplication {
    public static void main(String[] args) {
        SpringApplication.run(DormApplication.class);
    }
}
