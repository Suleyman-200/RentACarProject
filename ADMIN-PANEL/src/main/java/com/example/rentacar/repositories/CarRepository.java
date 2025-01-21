package com.example.rentacar.repositories;

import com.example.rentacar.models.Car;
import com.example.rentacar.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CarRepository extends JpaRepository<Car,Integer> {
    boolean existsCarsByPlate(String plate);
    List<Car> findByModel(Model model);
    List<Car> findByDailyPrise(double dailyPrise);
    Car findByPlate(String plate);
    List<Car> findByBodyType(String bodyType);

}
