package com.javaclimb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicPlayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicPlayerApplication.class, args);
    }

}
