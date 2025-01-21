package com.example.rentacar.business.concretes;

import com.example.rentacar.dto.request.FeedbackRequest;
import com.example.rentacar.mappers.ModelMapperService;
import com.example.rentacar.repositories.FeedBackRepository;

import com.example.rentacar.models.FeedBack;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackManager {
    private final FeedBackRepository feedbackRepository;
    private final ModelMapperService modelMapperService;



    public FeedBackManager(FeedBackRepository feedbackRepository, ModelMapperService modelMapperService) {
        this.feedbackRepository = feedbackRepository;
        this.modelMapperService = modelMapperService;
    }

    public void saveFeedback(FeedbackRequest feedback) {
     FeedBack feedback1=this.modelMapperService.forRequest().map(feedback,FeedBack.class);
     feedbackRepository.save(feedback1);
    }
    public List<FeedBack> getAllFeedback() {

        return feedbackRepository.findAll();
    }

}
