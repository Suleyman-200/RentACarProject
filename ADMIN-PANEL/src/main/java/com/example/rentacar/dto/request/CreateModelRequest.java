package com.example.rentacar.dto.request;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class  CreateModelRequest {
    @NotNull
    @NotBlank
    @Size(min = 3,max = 20)
    private String name;
    @NotNull
    private int brandId;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBrandId() {
        return brandId;
    }
    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }
}
