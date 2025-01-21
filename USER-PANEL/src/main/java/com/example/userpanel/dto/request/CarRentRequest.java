package com.example.userpanel.dto.request;


import javax.validation.constraints.NotNull;
import java.util.Date;

public class CarRentRequest {
    @NotNull

    private String carPlate;
    @NotNull

    private String userName;
    @NotNull
    private Date rentStartDate;
    @NotNull
    private Date rentEndDate;

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
