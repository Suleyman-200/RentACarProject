package com.example.userpanel.dto.response;




import java.util.List;



public class BrandDTO {

    private String brandName;
    private List<ModelDTO> models;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<ModelDTO> getModels() {
        return models;
    }

    public void setModels(List<ModelDTO> models) {
        this.models = models;
    }
}
