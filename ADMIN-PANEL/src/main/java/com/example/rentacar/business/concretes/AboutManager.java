package com.example.rentacar.business.concretes;

import com.example.rentacar.business.abstracts.AboutUsService;
import com.example.rentacar.dto.request.AboutUsRequest;
import com.example.rentacar.dto.request.UpdateAboutUsRequest;
import com.example.rentacar.dto.responses.GetAllAboutUsResponse;
import com.example.rentacar.exceptions.NotFoundException;
import com.example.rentacar.repositories.AboutUsRepository;
import com.example.rentacar.models.AboutUs;
import com.example.rentacar.mappers.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AboutManager implements AboutUsService {
    private final AboutUsRepository aboutUsRepository;
    private final ModelMapperService modelMapperService;

    public AboutManager(AboutUsRepository aboutUsRepository, ModelMapperService modelMapperService) {

        this.aboutUsRepository = aboutUsRepository;
        this.modelMapperService = modelMapperService;
    }



    @Override
    public List<GetAllAboutUsResponse> getAll() {
        List<AboutUs> aboutUsList = aboutUsRepository.findAll();
        return aboutUsList.stream()
                .map(aboutUs -> modelMapperService.forResponse().map(aboutUs, GetAllAboutUsResponse.class))
                .toList();
    }

    @Override
    public void add(AboutUsRequest aboutUsRequest) {
        AboutUs aboutUs=this.modelMapperService.forRequest().map(aboutUsRequest,AboutUs.class);
        aboutUsRepository.save(aboutUs);
    }

    @Override
    public void update(UpdateAboutUsRequest updateAboutUsRequest) {
        AboutUs aboutUs = aboutUsRepository.findById(updateAboutUsRequest.getId())
                .orElseThrow(() -> new NotFoundException("AboutUs not found"));


        aboutUs.setContent(updateAboutUsRequest.getContent());


        aboutUsRepository.save(aboutUs);

    }
}
