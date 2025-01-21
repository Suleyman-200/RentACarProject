package com.example.userpanel.controller;


import com.example.userpanel.dto.request.CreateUserRequest;
import com.example.userpanel.dto.request.LoginUserRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;


@Controller
@RequestMapping("/user")
public class UserController {
    private static final String BASE_URL = "http://localhost:8888/admin-panel/api/auth";
    Logger logger= LoggerFactory.getLogger(UserController.class);

    private final RestTemplate restTemplate;

    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody LoginUserRequest loginRequest) {
        String signInUrl = "http://localhost:8888/admin-panel/api/auth/signin";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<LoginUserRequest> requestEntity = new HttpEntity<>(loginRequest, headers);

        logger.info("Sign-in success "+loginRequest.getUserName());
        return restTemplate.exchange(signInUrl, HttpMethod.POST, requestEntity, String.class);
    }
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody CreateUserRequest signUpRequest) {
        String signUpUrl = BASE_URL + "/signup";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<CreateUserRequest> requestEntity = new HttpEntity<>(signUpRequest, headers);
        logger.info("Register Success "+signUpRequest.getUserName());
        return restTemplate.exchange(signUpUrl, HttpMethod.POST, requestEntity, String.class);
    }
}