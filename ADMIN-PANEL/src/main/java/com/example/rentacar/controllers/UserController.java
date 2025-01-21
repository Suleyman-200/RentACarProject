package com.example.rentacar.controllers;

import com.example.rentacar.business.abstracts.UserService;
import com.example.rentacar.dto.request.UpdateUserRequest;
import com.example.rentacar.dto.responses.GetAllUserResponse;
import com.example.rentacar.dto.responses.GetByIdUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<GetAllUserResponse>> getAll() {
        List<GetAllUserResponse> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetByIdUserResponse> getById(@PathVariable Long id) {
        GetByIdUserResponse user = userService.getById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("")
    public ResponseEntity<Void> update(@RequestBody UpdateUserRequest updateRequest) {
        userService.update(updateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
}
