package com.demo.microservicethree;

//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient(name = "service-b")
// interface ServiceBClient {
//
//    @GetMapping("/api/data")
//    String getData();
//}


@Configuration
class AppConfig {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

@Service
public class MyService {

    private RabbitTemplate rabbitTemplate;
    static final String QUEUE_NAME = "trainQueue";
    private static final String CIRCUIT_BREAKER_NAME = "serviceTwoCircuitBreaker";

    @Autowired
    private RestTemplate restTemplate;


    public MyService(RestTemplate restTemplate,RabbitTemplate rabbitTemplate) {
        System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");

        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendMessageToServiceTwo(String message) {
        rabbitTemplate.convertAndSend(QUEUE_NAME, message);
    }

}
