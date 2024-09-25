package com.trainPJ.booking;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigurationProperties;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4jBulkheadProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class R4jConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        return CircuitBreakerRegistry.ofDefaults();
    }

    @Bean
    public TimeLimiterRegistry timeLimiterRegistry() {
        return TimeLimiterRegistry.ofDefaults();
    }

    @Bean
    public Resilience4JCircuitBreakerFactory circuitBreakerFactory(CircuitBreakerRegistry circuitBreakerRegistry,
                                                                   TimeLimiterRegistry timeLimiterRegistry,
                                                                   Resilience4jBulkheadProvider bulkheadProvider,
                                                                   Resilience4JConfigurationProperties configurationProperties) {
        Resilience4JCircuitBreakerFactory factory = new Resilience4JCircuitBreakerFactory(circuitBreakerRegistry, timeLimiterRegistry, bulkheadProvider, configurationProperties);

        factory.configure(builder -> builder
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                        .failureRateThreshold(50)
                        .waitDurationInOpenState(Duration.ofMillis(500))
                        .slidingWindowSize(2)
                        .build())
                .timeLimiterConfig(TimeLimiterConfig.custom()
                        .timeoutDuration(Duration.ofSeconds(60))
                        .build()), "checkTransitServiceStatus");

        return factory;
    }
}
