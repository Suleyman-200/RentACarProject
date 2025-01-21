package com.example.rentacar.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




public class CreateBrandRequest {
    @NotNull
    @Size(min = 3,max = 20)
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
