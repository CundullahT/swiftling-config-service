package com.swiftling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SwiftlingConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwiftlingConfigServiceApplication.class, args);
    }

}
