package com.xshhope.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@EntityScan(value = "com.xshhope.model.pay")
@SpringBootApplication
public class PayCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayCenterApplication.class, args);
	}

}
