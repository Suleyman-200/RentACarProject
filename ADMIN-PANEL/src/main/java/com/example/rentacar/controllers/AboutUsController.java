package com.example.rentacar.controllers;

import com.example.rentacar.business.abstracts.AboutUsService;
import com.example.rentacar.dto.request.AboutUsRequest;
import com.example.rentacar.dto.request.UpdateAboutUsRequest;
import com.example.rentacar.dto.responses.GetAllAboutUsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AboutUsController {
    private final AboutUsService aboutUsService;

    public AboutUsController(AboutUsService aboutUsService) {
        this.aboutUsService = aboutUsService;
    }

    @GetMapping("/about-us/read")
    public ResponseEntity<List<GetAllAboutUsResponse>> getAll() {
        List<GetAllAboutUsResponse> aboutUsResponses = aboutUsService.getAll();
        return new ResponseEntity<>(aboutUsResponses, HttpStatus.OK);
    }

    @PostMapping("new/about-us/")
    public ResponseEntity<Void> add(@RequestBody AboutUsRequest aboutUsRequest) {
        aboutUsService.add(aboutUsRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/about-us")
    public ResponseEntity<Void> uptadeAboutUs(@RequestBody UpdateAboutUsRequest updateAboutUsRequest) {
        aboutUsService.update(updateAboutUsRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
