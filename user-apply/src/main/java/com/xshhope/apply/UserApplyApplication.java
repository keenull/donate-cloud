package com.xshhope.apply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@EntityScan("com.xshhope.model.apply")
@SpringBootApplication
public class UserApplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplyApplication.class, args);
	}

}
