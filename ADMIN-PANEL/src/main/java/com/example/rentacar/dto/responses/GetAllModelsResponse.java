package com.example.rentacar.dto.responses;




public class GetAllModelsResponse {
    private int id;
    private String modelName;
    private String brandName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return modelName;
    }

    public void setName(String name) {
        this.modelName = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
