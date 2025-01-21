package com.example.rentacar.dto.responses;




import java.util.List;



public class ModelDTO {

    private String modelName;
    private List<CarDTO> parametrs;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public List<CarDTO> getParametrs() {
        return parametrs;
    }

    public void setParametrs(List<CarDTO> parametrs) {
        this.parametrs = parametrs;
    }
}
