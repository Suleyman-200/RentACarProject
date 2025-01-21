package com.example.rentacar.business.concretes;

import com.example.rentacar.business.abstracts.BrandService;
import com.example.rentacar.dto.request.CreateBrandRequest;
import com.example.rentacar.dto.request.UpdateBrandRequest;
import com.example.rentacar.dto.responses.GetAllBrandsResponse;
import com.example.rentacar.dto.responses.GetByIdBrandResponse;
import com.example.rentacar.business.rules.BrandBusinessRules;


import com.example.rentacar.exceptions.NotFoundException;
import com.example.rentacar.mappers.ModelMapperService;
import com.example.rentacar.repositories.BrandRepository;
import com.example.rentacar.models.Brand;


import org.springframework.stereotype.Service;


import java.util.List;



@Service

public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;

    private final ModelMapperService modelMapperService;
    private final BrandBusinessRules brandBusinessRules;

    public BrandManager(BrandRepository brandRepository, ModelMapperService modelMapperService, BrandBusinessRules brandBusinessRules) {
        this.brandRepository = brandRepository;
        this.modelMapperService = modelMapperService;
        this.brandBusinessRules = brandBusinessRules;
    }

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();


        return brands.stream()
                .map(brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class))
                .toList();
    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {
        this.brandBusinessRules.checkIfBrandNameExsists(createBrandRequest.getName());
        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        brandRepository.save(brand);
    }


    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
        Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
        this.brandBusinessRules.checkIfBrandNameExsists(updateBrandRequest.getName());
        Brand brand1 = brandRepository.findById(updateBrandRequest.getId()).orElseThrow(() -> new NotFoundException("Couldn't not find id" + brand.getId()));
        brand1.setName(brand.getName());


        this.brandRepository.save(brand1);

    }

    @Override
    public GetByIdBrandResponse getById(int id) {
        Brand brand = this.brandRepository.findById(id).orElseThrow(()-> new NotFoundException("Not found this id "+id));

        return modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);
    }

    @Override
    public void delete(int id) {
        Brand brand = this.brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found this id " + id));

        this.brandRepository.delete(brand);
    }



}
