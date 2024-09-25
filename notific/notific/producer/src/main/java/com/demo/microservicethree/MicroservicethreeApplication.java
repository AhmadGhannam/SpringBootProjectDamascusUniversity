package com.demo.microservicethree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableEurekaClient
public class MicroservicethreeApplication {


	public static void main(String[] args) {
		SpringApplication.run(MicroservicethreeApplication.class, args);
	}

}
