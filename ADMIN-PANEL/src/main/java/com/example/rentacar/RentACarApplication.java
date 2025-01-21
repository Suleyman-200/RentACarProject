package com.example.rentacar;

import com.example.rentacar.config.WebMvcConfig;
import com.example.rentacar.exceptions.BusinessException;
import com.example.rentacar.exceptions.ExceptionResponseFeign;
import com.example.rentacar.exceptions.ProblemDetails;
import com.example.rentacar.exceptions.ValidationProblemDetails;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@SpringBootApplication
@Import(WebMvcConfig.class)
@EnableEurekaClient
public class RentACarApplication {


    public static void main(String[] args) {
        SpringApplication.run(RentACarApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper(){

        return new ModelMapper();
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public HttpHeaders httpHeaders(){
        return new HttpHeaders();
    }
}
