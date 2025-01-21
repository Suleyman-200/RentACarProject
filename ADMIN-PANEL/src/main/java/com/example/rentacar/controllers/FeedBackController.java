package com.example.rentacar.controllers;

import com.example.rentacar.business.concretes.FeedBackManager;
import com.example.rentacar.dto.request.FeedbackRequest;
import com.example.rentacar.models.FeedBack;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class FeedBackController {
    private final FeedBackManager feedBackManager;

    public FeedBackController(FeedBackManager feedBackManager) {
        this.feedBackManager = feedBackManager;
    }

    @PostMapping("/feedback")
    public ResponseEntity<Void> submitFeedback(@RequestBody FeedbackRequest feedbackRequest) {
        feedBackManager.saveFeedback(feedbackRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/feedbacks")
    public ResponseEntity<List<FeedBack>> getAllFeedbacks() {
        List<FeedBack> feedbacks = feedBackManager.getAllFeedback();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }
}
