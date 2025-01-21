package com.example.rentacar.business.abstracts;


import com.example.rentacar.dto.request.CreateModelRequest;

import com.example.rentacar.dto.request.UpdateModelRequest;
import com.example.rentacar.dto.responses.GetAllModelsResponse;

import java.util.List;

public interface ModelService {

    List<GetAllModelsResponse> getAll();
    void add(CreateModelRequest createModelRequest);
    void update(UpdateModelRequest updateModelRequest);
    void delete(int id);
}
