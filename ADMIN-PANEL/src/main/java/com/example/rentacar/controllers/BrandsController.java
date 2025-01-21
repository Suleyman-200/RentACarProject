package com.example.rentacar.controllers;

import com.example.rentacar.business.abstracts.BrandService;
import com.example.rentacar.dto.request.CreateBrandRequest;
import com.example.rentacar.dto.request.UpdateBrandRequest;
import com.example.rentacar.dto.responses.GetAllBrandsResponse;
import com.example.rentacar.dto.responses.GetByIdBrandResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/brands")
@Validated
@CrossOrigin("*")
public class BrandsController {
    private final BrandService brandService;

    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping()
    public ResponseEntity<List<GetAllBrandsResponse>> getAll() {
        List<GetAllBrandsResponse> brands = brandService.getAll();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetByIdBrandResponse> getById(@PathVariable int id) {
        GetByIdBrandResponse brand = brandService.getById(id);
        if (brand != null) {
            return new ResponseEntity<>(brand, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
        brandService.add(createBrandRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping()
    public ResponseEntity<Void> update(@RequestBody UpdateBrandRequest updateBrandRequest) {
        brandService.update(updateBrandRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable int id) {
        this.brandService.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
}

