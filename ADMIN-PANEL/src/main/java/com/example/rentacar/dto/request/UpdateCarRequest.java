package com.example.rentacar.dto.request;

import javax.validation.constraints.NotNull;

public class UpdateCarRequest {
    private int id;
    @NotNull
    private double dailyPrise;
    @NotNull
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDailyPrise() {
        return dailyPrise;
    }

    public void setDailyPrise(double dailyPrise) {
        this.dailyPrise = dailyPrise;
    }
}
