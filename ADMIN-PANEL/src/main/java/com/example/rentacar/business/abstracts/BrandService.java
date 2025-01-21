package com.example.rentacar.business.abstracts;

import com.example.rentacar.dto.request.CreateBrandRequest;
import com.example.rentacar.dto.request.UpdateBrandRequest;
import com.example.rentacar.dto.responses.GetAllBrandsResponse;
import com.example.rentacar.dto.responses.GetByIdBrandResponse;

import java.util.List;

public interface BrandService {

        List<GetAllBrandsResponse> getAll();
        GetByIdBrandResponse getById(int id);
        void add(CreateBrandRequest createBrandRequest);
        void update(UpdateBrandRequest updateBrandRequest);
        void delete(int id);




}
