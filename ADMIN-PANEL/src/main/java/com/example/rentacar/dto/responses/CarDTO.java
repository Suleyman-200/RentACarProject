package com.example.rentacar.dto.responses;

import java.util.Date;


public class CarDTO {


    private String plate;
    private double dailyPrise;
    private int modelYear;
    private double engine;
    private String bodyType;
    private int doors;
    private String transmission;
    private String driveTrain;
    private boolean isAvailable;
    private Date rendEndDate;
    private String fuelType;
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Date getRendEndDate() {
        return rendEndDate;
    }

    public void setRendEndDate(Date rendEndDate) {
        this.rendEndDate = rendEndDate;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
