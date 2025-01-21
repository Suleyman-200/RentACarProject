package com.example.rentacar.controllers;

import com.example.rentacar.business.abstracts.ModelService;
import com.example.rentacar.dto.request.CreateModelRequest;
import com.example.rentacar.dto.request.UpdateModelRequest;
import com.example.rentacar.dto.responses.GetAllModelsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/models")
public class ModelsController {
    private final ModelService modelService;

    public ModelsController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping()
    public ResponseEntity<List<GetAllModelsResponse>> getAll() {
        List<GetAllModelsResponse> models = modelService.getAll();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody @Valid CreateModelRequest createModelRequest) {
        modelService.add(createModelRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping()
    public ResponseEntity<Void> update(@RequestBody @Valid UpdateModelRequest updateModelRequest) {
        modelService.update(updateModelRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable int id) {
        modelService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
