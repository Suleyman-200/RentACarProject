package com.example.userpanel.controller;

import com.example.userpanel.dto.request.CarRentRequest;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.CheckedFunction0;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequestMapping("/rentcar")
public class RentCarController {
    private final RestTemplate restTemplate;
    private static final String WEBURL = "http://localhost:8888/admin-panel/api/rents/rent-car";
    private static final boolean VALID_TOKEN = true;
    Logger logger= LoggerFactory.getLogger(RentCarController.class);

    private final CircuitBreaker circuitBreaker;

    public RentCarController(RestTemplate restTemplate, CircuitBreaker circuitBreaker) {
        this.restTemplate = restTemplate;
        this.circuitBreaker = circuitBreaker;
    }

    @PostMapping("/order")
    public ResponseEntity<String> rentCar(@Valid @RequestBody CarRentRequest carRentRequest, @RequestHeader("Authorization") String authorizationHeader) {
        if (isValidToken()) {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", authorizationHeader);

            HttpEntity<CarRentRequest> requestEntity = new HttpEntity<>(carRentRequest, headers);


            CheckedFunction0<ResponseEntity<String>> supplier = CircuitBreaker.decorateCheckedSupplier(circuitBreaker,
                    () -> restTemplate.exchange(WEBURL, HttpMethod.POST, requestEntity, String.class)
            );
            try {
                ResponseEntity<String> response = supplier.apply();

                return ResponseEntity.ok(response.getBody());
            } catch (Throwable throwable) {
                if (throwable instanceof HttpClientErrorException.Unauthorized) {

                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please enter account");
                } else {

                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Car already rented");
                }
            }
        } else {
            logger.error("Unknown problem please check");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unknown problem please check");
        }
    }

    private boolean isValidToken() {
        return VALID_TOKEN;
    }
}
