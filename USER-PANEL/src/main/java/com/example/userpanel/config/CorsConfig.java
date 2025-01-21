package com.example.userpanel.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange; // Gerekli import eklenmeli
import org.springframework.web.server.WebFilter; // Gerekli import eklenmeli
import reactor.core.publisher.Mono;

@Configuration
public class CorsConfig {

    @Bean
    public WebFilter corsFilter() {
        return (exchange, chain) -> {
            ServerWebExchange exchangeObject = (ServerWebExchange) exchange; // Cast işlemi yapılmalı
            ServerHttpRequest request = exchangeObject.getRequest();
            ServerHttpResponse response = exchangeObject.getResponse();
            HttpHeaders headers = response.getHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
            headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");

            if (request.getMethod() == HttpMethod.OPTIONS) {
                response.setStatusCode(HttpStatus.OK);
                return Mono.empty();
            } else {
                return chain.filter(exchange);
            }
        };
    }
}

