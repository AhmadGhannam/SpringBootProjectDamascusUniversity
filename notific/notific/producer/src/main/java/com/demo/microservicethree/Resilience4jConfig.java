//package com.demo.microservicethree;
//
//import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
//import io.github.resilience4j.timelimiter.TimeLimiterConfig;
//import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.Duration;
////This annotation marks the class as a source of bean definitions for the Spring context.
//@Configuration
//public class Resilience4jConfig {
//
////    CircuitBreakerConfig and TimeLimiterConfig:
////    These classes are used to configure the behavior of the circuit breaker and time limiter.
//
////    Bean and Configuration:
////    These annotations are used to define a Spring configuration class and declare Spring beans.
//
//    @Bean
//    public Resilience4JCircuitBreakerFactory circuitBreakerFactory() {
////        Create Factory:
//        Resilience4JCircuitBreakerFactory factory = new Resilience4JCircuitBreakerFactory();
////        failureRateThreshold(50): Sets the threshold of failures that will trip the circuit breaker to an open state.
////        Here, if 50% of requests fail, the circuit breaker will open.
////        waitDurationInOpenState(Duration.ofMillis(1000)):
////        Sets the duration the circuit breaker will stay open before transitioning to a half-open state
////        and allowing a limited number of test requests to pass through.
////        In this case, 1000 milliseconds (1 second).
////        slidingWindowSize(2): Defines the number of calls that are recorded when evaluating the failure rate.
//        factory.configure(builder -> builder
//                .circuitBreakerConfig(CircuitBreakerConfig.custom()
//                        .failureRateThreshold(50)
//                        .waitDurationInOpenState(Duration.ofMillis(10000))
//                        .slidingWindowSize(2)
//                        .build())
////                timeoutDuration(Duration.ofSeconds(4)):
////                Sets the timeout duration for the circuit breaker to wait for a response.
////                If a response is not received within 4 seconds, it is considered a failure.
//                .timeLimiterConfig(TimeLimiterConfig.custom()
//                        .timeoutDuration(Duration.ofSeconds(40))
//                        .build()), "myCircuitBreaker");
//        return factory;
//    }
//}
