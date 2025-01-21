package com.example.rentacar.controllers;

import com.example.rentacar.business.abstracts.CarService;
import com.example.rentacar.dto.request.CreateCarRequest;
import com.example.rentacar.dto.request.UpdateCarRequest;
import com.example.rentacar.dto.responses.GetAllCarsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin("*")
public class CarsController {
    private CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public ResponseEntity<List<GetAllCarsResponse>> getAll() {
        List<GetAllCarsResponse> cars = carService.getAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody @Valid CreateCarRequest createCarRequest) {
        carService.add(createCarRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping()
    public ResponseEntity<Void> update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
        carService.update(updateCarRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
   public ResponseEntity<Integer> delete(int id){
        this.carService.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);

    }
}
