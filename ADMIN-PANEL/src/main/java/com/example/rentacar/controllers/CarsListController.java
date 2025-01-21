package com.example.rentacar.controllers;
import com.example.rentacar.business.concretes.RentACarService;
import com.example.rentacar.dto.responses.BrandDTO;
import com.example.rentacar.dto.responses.CarDTOForFilter;
import com.example.rentacar.dto.responses.ModelDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/list")
@Slf4j
@CrossOrigin("*")
public class CarsListController {
    Logger logger= LoggerFactory.getLogger(CarsListController.class);
    private final RentACarService rentACarService;

    public CarsListController(RentACarService rentACarService) {
        this.rentACarService = rentACarService;
    }

    @GetMapping()
    public List<BrandDTO> getAllData() {
        return rentACarService.getAllData();
    }

    @GetMapping("/brand/{name}")
    public ResponseEntity<Object> getModelsAndCarsByBrandName(@PathVariable String name) {
        ResponseEntity<Object> response;
        List<ModelDTO> modelDTOs = rentACarService.getModelsAndCarsByBrandName(name);

        if (!modelDTOs.isEmpty()) {
            response = ResponseEntity.ok(modelDTOs);
        } else {
            String errorMessage = "Cannot find Brand name" + "=" + name;
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            logger.error(errorMessage);
        }

        return response;
    }



    @GetMapping("/brand/dailyPrise/{dailyPrise}")
    public ResponseEntity<Object> getDailyPrise(@PathVariable double dailyPrise) {
        ResponseEntity<Object> response;
        List<CarDTOForFilter> carDTOForDailyPrises = rentACarService.getByDailyPrise(dailyPrise);
        if (!carDTOForDailyPrises.isEmpty()) {
            response = ResponseEntity.ok(carDTOForDailyPrises);
        } else {
            String errorMessage = "No cars at this price" + "=" + dailyPrise;
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            logger.error(errorMessage);
        }
        return response;
    }

    @GetMapping("/brand/bodytype/{bodyType}")
    public ResponseEntity<Object> getByBodyType(@PathVariable String bodyType){
        ResponseEntity<Object> response;
        List<CarDTOForFilter> carDTOForDailyPrises = rentACarService.getByBodyType(bodyType);
        if (!carDTOForDailyPrises.isEmpty()) {
            response = ResponseEntity.ok(carDTOForDailyPrises);
        } else {
            String errorMessage = "No cars at this bodytype" + "=" + bodyType;
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            logger.error(errorMessage);
        }
        return response;
    }


}