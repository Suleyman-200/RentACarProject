package com.example.rentacar.business.concretes;

import com.example.rentacar.business.concretes.converter.CarToCarDTOConverter;

import com.example.rentacar.dto.responses.BrandDTO;
import com.example.rentacar.dto.responses.CarDTO;
import com.example.rentacar.dto.responses.CarDTOForFilter;
import com.example.rentacar.dto.responses.ModelDTO;
import com.example.rentacar.repositories.BrandRepository;
import com.example.rentacar.repositories.CarRepository;

import com.example.rentacar.models.Brand;
import com.example.rentacar.models.Car;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j

public class RentACarService {

    private final BrandRepository brandRepository;

    private final CarRepository carRepository;
    private final CarToCarDTOConverter carToCarDTOConverter;

    public RentACarService(BrandRepository brandRepository, CarRepository carRepository, CarToCarDTOConverter carToCarDTOConverter) {
        this.brandRepository = brandRepository;

        this.carRepository = carRepository;
        this.carToCarDTOConverter = carToCarDTOConverter;
    }

    public List<BrandDTO> getAllData() {
        List<Brand> brands = brandRepository.findAll();

        return brands.stream()
                .map(brand -> {
                    BrandDTO brandDTO = new BrandDTO();
                    brandDTO.setBrandName(brand.getName());

                    List<ModelDTO> modelDTOs = brand.getModels().stream()
                            .map(model -> {
                                ModelDTO modelDTO = new ModelDTO();
                                modelDTO.setModelName(model.getName());

                                List<CarDTO> carDTOs = model.getCars().stream()
                                        .map(car -> {
                                            CarDTO carDTO = new CarDTO();
                                            carDTO.setPlate(car.getPlate());
                                            carDTO.setDailyPrise(car.getDailyPrise());
                                            carDTO.setModelYear(car.getModelYear());
                                            carDTO.setEngine(car.getEngine());
                                            carDTO.setBodyType(car.getBodyType());
                                            carDTO.setDoors(car.getDoors());
                                            carDTO.setLink(car.getLink());
                                            carDTO.setTransmission(car.getTransmission());
                                            carDTO.setDriveTrain(car.getDriveTrain());
                                            carDTO.setAvailable(car.getAvailable());
                                            carDTO.setRendEndDate(car.getRentEndDate());
                                            carDTO.setFuelType(car.getFuelType());
                                            return carDTO;
                                        })
                                        .collect(Collectors.toList());

                                modelDTO.setParametrs(carDTOs);
                                return modelDTO;
                            })
                            .collect(Collectors.toList());

                    brandDTO.setModels(modelDTOs);
                    return brandDTO;
                })
                .collect(Collectors.toList());
    }

    public List<ModelDTO> getModelsAndCarsByBrandName(String brandName) {
        List<Brand> brands = brandRepository.findAll();

        return brands.stream()
                .filter(brand -> brand.getName().equalsIgnoreCase(brandName))
                .flatMap(brand -> brand.getModels().stream())
                .map(model -> {
                    ModelDTO modelDTO = new ModelDTO();
                    modelDTO.setModelName(model.getName());

                    List<CarDTO> carDTOs = model.getCars().stream()
                            .map(car -> {
                                CarDTO carDTO = new CarDTO();
                                carDTO.setPlate(car.getPlate());
                                carDTO.setDailyPrise(car.getDailyPrise());
                                carDTO.setModelYear(car.getModelYear());
                                carDTO.setEngine(car.getEngine());
                                carDTO.setBodyType(car.getBodyType());
                                carDTO.setDoors(car.getDoors());
                                carDTO.setLink(car.getLink());
                                carDTO.setTransmission(car.getTransmission());
                                carDTO.setDriveTrain(car.getDriveTrain());
                                carDTO.setAvailable(car.getAvailable());
                                carDTO.setRendEndDate(car.getRentEndDate());
                                carDTO.setFuelType(car.getFuelType());
                                return carDTO;
                            })
                            .collect(Collectors.toList());

                    modelDTO.setParametrs(carDTOs);
                    return modelDTO;
                })
                .collect(Collectors.toList());
    }

    public List<CarDTOForFilter> getByDailyPrise(double dailyPrise) {
        List<Car> cars = carRepository.findByDailyPrise(dailyPrise);

        return cars.stream()
                .filter(car -> car.getModel() != null && car.getModel().getBrand() != null)
                .map(car -> {
                    CarDTOForFilter carDTOForFilter = carToCarDTOConverter.convert(car);
                    carDTOForFilter.setBrandName(car.getModel().getBrand().getName());
                    carDTOForFilter.setModelName(car.getModel().getName());
                    carDTOForFilter.setAvailable(car.getAvailable());
                    carDTOForFilter.setLink(car.getLink());
                    carDTOForFilter.setRendEndDate(car.getRentEndDate());
                    carDTOForFilter.setFuelType(car.getFuelType());
                    return carDTOForFilter;
                })
                .collect(Collectors.toList());
    }
    public List<CarDTOForFilter> getByBodyType (String bodyType) {
        List<Car> cars=  carRepository.findByBodyType(bodyType);

        return cars.stream()
                .filter(car -> car.getModel() != null && car.getModel().getBrand() != null)
                .map(car -> {
                    CarDTOForFilter carDTOForFilter = carToCarDTOConverter.convert(car);
                    carDTOForFilter.setBrandName(car.getModel().getBrand().getName());
                    carDTOForFilter.setModelName(car.getModel().getName());
                    carDTOForFilter.setAvailable(car.getAvailable());
                    carDTOForFilter.setRendEndDate(car.getRentEndDate());
                    carDTOForFilter.setFuelType(car.getFuelType());
                    carDTOForFilter.setLink(car.getLink());
                    return carDTOForFilter;
                })
                .collect(Collectors.toList());
    }
}