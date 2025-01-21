package com.example.rentacar.business.rules;

import com.example.rentacar.exceptions.BusinessException;
import com.example.rentacar.repositories.CarRepository;


import org.springframework.stereotype.Service;


@Service

public class CarBusinessRules {
  private final  CarRepository carRepository;

    public CarBusinessRules(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void existsCarsByPlate(String plate){
      if (this.carRepository.existsCarsByPlate(plate)) {
          throw new BusinessException("Plate already used");
      }
  }

}
