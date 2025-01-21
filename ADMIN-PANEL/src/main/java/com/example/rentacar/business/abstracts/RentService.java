package com.example.rentacar.business.abstracts;

import com.example.rentacar.dto.request.RentCarRequest;
import com.example.rentacar.dto.request.UpdateRentRequest;
import com.example.rentacar.models.Rent;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RentService {
    ResponseEntity<String> rentCar(RentCarRequest rentCarRequest);
    List<Rent> getAllRents();
    void delete(Long id);
    void update(UpdateRentRequest updateRentRequest);
}
