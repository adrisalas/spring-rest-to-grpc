package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class GrpcToDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcToDatabaseApplication.class, args);
    }

}
