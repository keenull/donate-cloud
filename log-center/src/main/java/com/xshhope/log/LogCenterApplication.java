package com.xshhope.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LogCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogCenterApplication.class, args);
    }

}
