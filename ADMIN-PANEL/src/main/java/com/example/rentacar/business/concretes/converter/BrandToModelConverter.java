package com.example.rentacar.business.concretes.converter;


import com.example.rentacar.models.Brand;
import com.example.rentacar.models.Model;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BrandToModelConverter implements Converter<Brand, Model> {

    @Override
    public Model convert(Brand brand) {
        Model model = new Model();
        model.setId(brand.getId());
        model.setName(brand.getName());
        return model;
    }
}

