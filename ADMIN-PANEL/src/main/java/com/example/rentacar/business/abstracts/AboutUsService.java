package com.example.rentacar.business.abstracts;

import com.example.rentacar.dto.request.AboutUsRequest;
import com.example.rentacar.dto.request.UpdateAboutUsRequest;
import com.example.rentacar.dto.responses.GetAllAboutUsResponse;

import java.util.List;


public interface AboutUsService {
    List <GetAllAboutUsResponse> getAll();
    void add(AboutUsRequest aboutUsRequest);
    void update(UpdateAboutUsRequest updateAboutUsRequest);
}
