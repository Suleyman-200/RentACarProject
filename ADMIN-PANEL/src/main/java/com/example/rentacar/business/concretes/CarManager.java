package com.example.rentacar.business.concretes;

import com.example.rentacar.business.abstracts.CarService;
import com.example.rentacar.dto.request.CreateCarRequest;
import com.example.rentacar.dto.request.UpdateCarRequest;
import com.example.rentacar.dto.responses.GetAllCarsResponse;

import com.example.rentacar.business.rules.CarBusinessRules;
import com.example.rentacar.exceptions.NotFoundException;
import com.example.rentacar.mappers.ModelMapperService;
import com.example.rentacar.repositories.CarRepository;
import com.example.rentacar.models.Car;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CarManager implements CarService {
    private CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private CarBusinessRules carBusinessRules;

    public CarManager(CarRepository carRepository, ModelMapperService modelMapperService, CarBusinessRules carBusinessRules) {
        this.carRepository = carRepository;
        this.modelMapperService = modelMapperService;
        this.carBusinessRules = carBusinessRules;
    }

    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars = carRepository.findAll();



        return cars.stream().map(car -> this.modelMapperService.forResponse().map(car, GetAllCarsResponse.class)).toList();
    }

    @Override
    public void add(CreateCarRequest createCarRequest) {
        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
        this.carBusinessRules.existsCarsByPlate(createCarRequest.getPlate());
        this.carRepository.save(car);
    }

    @Override
    public void update(UpdateCarRequest updateCarRequest) {
        Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
        Car car1 = carRepository.findById(updateCarRequest.getId())
                .orElseThrow(() -> new NotFoundException("Couldn't find car with this id: " + car.getId()));
        car1.setDailyPrise(car.getDailyPrise());
        car1.setLink(car.getLink());
        this.carRepository.save(car1);


    }

    @Override
    public void delete(int id) {
    Car car =this.carRepository.findById(id).orElseThrow(()->new NotFoundException("Couldn't find car with this id:"+id));
    this.carRepository.delete(car);

    }

}
















