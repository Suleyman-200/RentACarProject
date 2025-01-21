package com.example.userpanel;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;


import org.springframework.web.client.RestTemplate;



@SpringBootApplication
@EnableJpaRepositories
public class UserPanelApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserPanelApplication.class, args);
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