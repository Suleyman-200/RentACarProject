package com.example.rentacar.dto.request;




import javax.validation.constraints.NotNull;


public class CreateCarRequest {
    @NotNull
    private int modelId;
    @NotNull
    private String plate;
    @NotNull
    private double dailyPrise;
    @NotNull
    private int modelYear;
    @NotNull
    private double engine;
    @NotNull
    private String bodyType;
    @NotNull
    private int doors;
    @NotNull
    private String transmission;
    @NotNull
    private String driveTrain;
    @NotNull
    private String fuelType;
    @NotNull
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public double getDailyPrise() {
        return dailyPrise;
    }

    public void setDailyPrise(double dailyPrise) {
        this.dailyPrise = dailyPrise;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public double getEngine() {
        return engine;
    }

    public void setEngine(double engine) {
        this.engine = engine;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDriveTrain() {
        return driveTrain;
    }

    public void setDriveTrain(String driveTrain) {
        this.driveTrain = driveTrain;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
