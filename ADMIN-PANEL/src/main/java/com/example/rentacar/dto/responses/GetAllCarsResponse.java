package com.example.rentacar.dto.responses;



import java.util.Date;



public class GetAllCarsResponse {
        private int id;
        private String modelName;
        private int modelId;
        private String plate;
        private double dailyPrise;
        private int modelYear;
        private double engine;
        private String bodyType;
        private int doors;
        private String transmission;
        private String driveTrain;
        private Date rentEndDate;
        private Boolean isAvailable;
        private String fuelType;
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

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
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

        public Date getRentEndDate() {
            return rentEndDate;
        }

        public void setRentEndDate(Date rentEndDate) {
            this.rentEndDate = rentEndDate;
        }

        public Boolean getAvailable() {
            return isAvailable;
        }

        public void setAvailable(Boolean available) {
            isAvailable = available;
        }

        public String getFuelType() {
            return fuelType;
        }

        public void setFuelType(String fuelType) {
            this.fuelType = fuelType;
        }
}
