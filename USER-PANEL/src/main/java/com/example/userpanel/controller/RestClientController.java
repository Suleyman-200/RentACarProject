package com.example.userpanel.controller;

import com.example.userpanel.dto.request.FeedBackRequest;
import com.example.userpanel.dto.response.*;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Slf4j
@Controller
@RequestMapping("/menu")
public class RestClientController {
    Logger logger = LoggerFactory.getLogger(RestClientController.class);

    private final RestTemplate restTemplate;

    public RestClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String WEBURL1 = "http://localhost:8888/admin-panel/api/list";

    @GetMapping("/allcars")
    @CircuitBreaker(name = "adminPanelCircuitBreaker")
    @ResponseBody
    public ResponseEntity<List<BrandDTO>> getAllData() {
        ResponseEntity<BrandDTO[]> response = restTemplate.getForEntity(WEBURL1, BrandDTO[].class);
        BrandDTO[] brandDTOs = response.getBody();
        return new ResponseEntity<>(Arrays.asList(brandDTOs), HttpStatus.OK);
    }

    @GetMapping("/brand/{brandName}")
    @CircuitBreaker(name = "adminPanelCircuitBreaker")
    @ResponseBody
    public ResponseEntity<?> getModelsAndCarsByBrandName(@PathVariable("brandName") String brandName) {
        String apiUrl = WEBURL1 + "/brand/" + brandName;
        ResponseEntity<ModelDTO[]> response;
        try {
            response = restTemplate.getForEntity(apiUrl, ModelDTO[].class);
            ModelDTO[] modelDTOs = response.getBody();
            return new ResponseEntity<>(Arrays.asList(modelDTOs), HttpStatus.OK);
        } catch (HttpClientErrorException.NotFound ex) {
            String errorMessage = "No models and cars found for the brand: " + brandName;
            logger.error(errorMessage);
            return new ResponseEntity<>(new MessageResponse(errorMessage), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/dailyprise/{dailyPrise}")
    @CircuitBreaker(name = "adminPanelCircuitBreaker")
    @ResponseBody
    public ResponseEntity<?> getByDailyPrise(@PathVariable("dailyPrise") double dailyPrise) {
        String apiUrl = WEBURL1 + "/brand/dailyPrise/" + dailyPrise;
        ResponseEntity<CarDTOForFilter[]> response;
        try {
            response = restTemplate.getForEntity(apiUrl, CarDTOForFilter[].class);
            CarDTOForFilter[] carDTOForDailyPrises = response.getBody();
            return new ResponseEntity<>(Arrays.asList(carDTOForDailyPrises), HttpStatus.OK);
        } catch (HttpClientErrorException ex) {
            String errorMessage = "No brand, models, and cars found for the dailyprise: " + dailyPrise;
            logger.error(errorMessage);
            return new ResponseEntity<>(new MessageResponse(errorMessage), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/feedback")
    @CircuitBreaker(name = "adminPanelCircuitBreaker")
    @ResponseBody
    public ResponseEntity<MessageResponse> feedback(@RequestBody FeedBackRequest feedBackDTO) {
        String webUrl = "http://localhost:8888/admin-panel/api/feedback";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        HttpEntity<FeedBackRequest> requestEntity = new HttpEntity<>(feedBackDTO, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(webUrl, HttpMethod.POST, requestEntity, String.class);
        MessageResponse feedBackResponse = new MessageResponse("We will take note of your thoughts, Thank you for your feedbacks");
        logger.info("User " + feedBackDTO.getUserName() + " send feedback");
        return new ResponseEntity<>(feedBackResponse, HttpStatus.OK);
    }

    @GetMapping("contact-us")
    @CircuitBreaker(name = "adminPanelCircuitBreaker")
    @ResponseBody
    public ResponseEntity<List<ContactDTO>> contactUs() {
        String webUrl = "http://localhost:8888/admin-panel/api/contact/all";
        ResponseEntity<ContactDTO[]> response = restTemplate.getForEntity(webUrl, ContactDTO[].class);
        ContactDTO[] contactUsDTOS = response.getBody();
        return new ResponseEntity<>(Arrays.asList(contactUsDTOS), HttpStatus.OK);
    }

    @GetMapping("about-us")
    @CircuitBreaker(name = "adminPanelCircuitBreaker")
    @ResponseBody
    public ResponseEntity<List<AboutUsDTO>> aboutUs() {
        String webUrl = "http://localhost:8888/admin-panel/api/about-us/read";
        ResponseEntity<AboutUsDTO[]> response = restTemplate.getForEntity(webUrl, AboutUsDTO[].class);
        AboutUsDTO[] aboutUs = response.getBody();
        return new ResponseEntity<>(Arrays.asList(aboutUs), HttpStatus.OK);
    }
}
