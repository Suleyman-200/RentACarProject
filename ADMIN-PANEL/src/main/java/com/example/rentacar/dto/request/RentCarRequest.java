package com.example.rentacar.dto.request;



import com.example.rentacar.models.Car;
import com.example.rentacar.models.User;

import javax.validation.constraints.NotNull;
import java.util.Date;


public class RentCarRequest {
    @NotNull
    private String carPlate;
    @NotNull
    private String userName;
    @NotNull
    private Date rentStartDate;
    @NotNull
    private Date rentEndDate;
    private User user;
    private Car car;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(Date rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public Date getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(Date rentEndDate) {
        this.rentEndDate = rentEndDate;
    }
}