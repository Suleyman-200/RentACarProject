package com.example.rentacar.business.abstracts;

import com.example.rentacar.dto.request.CreateUserRequest;
import com.example.rentacar.dto.request.UpdateUserRequest;
import com.example.rentacar.dto.responses.GetAllUserResponse;
import com.example.rentacar.dto.responses.GetByIdUserResponse;


import java.util.List;

public interface UserService {
    List <GetAllUserResponse> getAll();
    GetByIdUserResponse getById(Long id);
    void add(CreateUserRequest createUserRequest);
    void update(UpdateUserRequest updateUserRequest);



    void delete(Long id);

}
