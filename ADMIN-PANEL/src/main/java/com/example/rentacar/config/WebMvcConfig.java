package com.example.rentacar.config;

import com.example.rentacar.business.concretes.converter.BrandToModelConverter;
import com.example.rentacar.business.concretes.converter.CarToCarDTOConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebMvcConfig implements WebMvcConfigurer {

    private final BrandToModelConverter brandToModelConverter;
    private final CarToCarDTOConverter carToCarDTOConverter;

    public WebMvcConfig(BrandToModelConverter brandToModelConverter, CarToCarDTOConverter carToCarDTOConverter) {
        this.brandToModelConverter = brandToModelConverter;
        this.carToCarDTOConverter = carToCarDTOConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(brandToModelConverter);
        registry.addConverter(carToCarDTOConverter);
    }
}

