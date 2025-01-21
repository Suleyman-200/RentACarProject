package com.example.rentacar.business.rules;

import com.example.rentacar.exceptions.BusinessException;
import com.example.rentacar.repositories.BrandRepository;

import org.springframework.stereotype.Service;
@Service
public class BrandBusinessRules {
    private final BrandRepository brandRepository;

    public BrandBusinessRules(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public void checkIfBrandNameExsists(String name)
    {
        if(this.brandRepository.existsByName(name)){
                throw new BusinessException("Brand already exsists");
        }
    }
    }

