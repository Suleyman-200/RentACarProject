package com.example.rentacar.business.concretes;

import com.example.rentacar.business.abstracts.ModelService;
import com.example.rentacar.dto.request.CreateModelRequest;
import com.example.rentacar.dto.request.UpdateModelRequest;
import com.example.rentacar.dto.responses.GetAllModelsResponse;
import com.example.rentacar.exceptions.NotFoundException;
import com.example.rentacar.repositories.BrandRepository;
import com.example.rentacar.models.Brand;
import com.example.rentacar.mappers.ModelMapperService;
import com.example.rentacar.repositories.ModelRepository;
import com.example.rentacar.models.Model;

import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private BrandRepository brandRepository;

    public ModelManager(ModelRepository modelRepository, ModelMapperService modelMapperService, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.modelMapperService = modelMapperService;
        this.brandRepository = brandRepository;
    }

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = modelRepository.findAll();


        return models.stream()
                .map(model -> modelMapperService.forResponse().map(model, GetAllModelsResponse.class))
                .toList();
    }

    @Override
    public void add(CreateModelRequest createModelRequest) {
        int brandId = createModelRequest.getBrandId();

        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new IllegalArgumentException("Can't find this brand id"));

        Model newModel = new Model();
        newModel.setName(createModelRequest.getName());
        newModel.setBrand(brand);
        modelRepository.save(newModel);
        List<Model> existingModels = brand.getModels();

        existingModels.add(newModel);


        brand.setModels(existingModels);
        brandRepository.save(brand);
    }


    @Override
    public void update(UpdateModelRequest updateModelRequest) {
        Model model=this.modelMapperService.forRequest().map(updateModelRequest,Model.class);
        Model model1=modelRepository.findById(updateModelRequest.getId()).orElseThrow(()->new NotFoundException("Can't find this model id"+model.getId()));
        model1.setName(model.getName());

       this.modelRepository.save(model1);
    }

    @Override
    public void delete(int id) {
        Model model=modelRepository.findById(id).orElseThrow(()->new NotFoundException("Can't find this model id"+id));

        this.modelRepository.delete(model);
    }


}
