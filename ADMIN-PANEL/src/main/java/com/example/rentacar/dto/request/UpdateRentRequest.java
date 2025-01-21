package com.example.rentacar.dto.request;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class UpdateRentRequest {
    Long id;
    @NotNull
    private Date rentStartDate;
    @NotNull
    private Date rentEndDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
