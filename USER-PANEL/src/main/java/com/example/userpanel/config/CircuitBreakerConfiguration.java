package com.example.userpanel.config;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfiguration {

    @Bean
    public CircuitBreaker circuitBreaker() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(1))
                .permittedNumberOfCallsInHalfOpenState(10)
                .slidingWindowSize(10)
                .build();

        return CircuitBreaker.of("adminPanelCircuitBreaker", circuitBreakerConfig);
    }
}


