package com.example.rentacar.repositories;

import com.example.rentacar.models.Car;
import com.example.rentacar.models.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByCarAndRentEndDateGreaterThanEqual(Car car, Date rentStartDate);

}
