package com.example.rentacar.business.concretes.converter;


import com.example.rentacar.dto.responses.CarDTOForFilter;
import com.example.rentacar.models.Car;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
@Component
public class CarToCarDTOConverter implements Converter<Car, CarDTOForFilter> {
    @Override
    public CarDTOForFilter convert(Car car) {
        CarDTOForFilter carDTOForFilter = new CarDTOForFilter();
        carDTOForFilter.setPlate(car.getPlate());
        carDTOForFilter.setDailyPrise(car.getDailyPrise());
        carDTOForFilter.setModelYear(car.getModelYear());
        carDTOForFilter.setEngine(car.getEngine());
        carDTOForFilter.setBodyType(car.getBodyType());
        carDTOForFilter.setDoors(car.getDoors());
        carDTOForFilter.setTransmission(car.getTransmission());
        carDTOForFilter.setDriveTrain(car.getDriveTrain());
        return carDTOForFilter;
    }
}
