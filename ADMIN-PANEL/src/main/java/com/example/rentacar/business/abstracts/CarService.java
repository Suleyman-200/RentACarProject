package com.example.rentacar.business.abstracts;
import com.example.rentacar.dto.request.CreateCarRequest;
import com.example.rentacar.dto.request.UpdateCarRequest;
import com.example.rentacar.dto.responses.GetAllCarsResponse;
import java.util.List;
public interface CarService {
    List<GetAllCarsResponse> getAll();
    void add(CreateCarRequest createCarRequest);
    void update(UpdateCarRequest updateCarRequest);
    void delete(int id);
}
