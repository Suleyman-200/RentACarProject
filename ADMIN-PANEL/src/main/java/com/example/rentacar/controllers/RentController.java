package com.example.rentacar.controllers;

import com.example.rentacar.business.abstracts.RentService;
import com.example.rentacar.dto.request.RentCarRequest;
import com.example.rentacar.dto.request.UpdateRentRequest;
import com.example.rentacar.models.Rent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/rents")
public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @PostMapping("/rent-car")
    public ResponseEntity<String> rentCar(@RequestBody RentCarRequest rentCarRequest) {
        ResponseEntity<String> response = rentService.rentCar(rentCarRequest);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rent>> getAllRents() {
        List<Rent> rents = rentService.getAllRents();
        return ResponseEntity.ok(rents);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            rentService.delete(id);
            return ResponseEntity.ok("Rent with ID: " + id + " deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }

    }
    @PatchMapping("/uptade")
    public void uptade(@RequestBody UpdateRentRequest updateRentRequest){
        this.rentService.update(updateRentRequest);
    }
}