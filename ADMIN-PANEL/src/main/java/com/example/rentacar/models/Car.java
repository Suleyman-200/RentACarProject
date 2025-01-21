package com.example.rentacar.models;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name="cars")


@Entity
public class Car implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name = "plate")
        @NotNull
        private String plate;

        @Column(name = "daily_prise")
        @NotNull
        private double dailyPrise;

        @Column(name = "modelYear")
        @NotNull
        private int modelYear;

        @Column(name = "engine")
        @NotNull
        private double engine;

        @Column(name = "bodytype")
        @NotNull
        private String bodyType;

        @Column(name = "doors")
        @NotNull
        private int doors;

        @Column(name = "transmission")
        @NotNull
        private String transmission;

        @Column(name = "drivetrain")
        @NotNull
        private String driveTrain;
        @Column(name = "is_available")

        private Boolean isAvailable;
        @Column(name = "fueltype")
        @NotNull
        private String fuelType;
        @Column(name = "imageLink")
        @NotNull
        private String link;



        @Column(name = "rentEndDate")
        private Date rentEndDate;


        public Car() {

                this.isAvailable = true;
        }

        @ManyToOne
        @JoinColumn(name = "model_id")
        private Model model;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
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

        public Date getRentEndDate() {
                return rentEndDate;
        }

        public void setRentEndDate(Date rentEndDate) {
                this.rentEndDate = rentEndDate;
        }

        public Model getModel() {
                return model;
        }

        public void setModel(Model model) {
                this.model = model;
        }

        public String getLink() {
                return link;
        }

        public void setLink(String link) {
                this.link = link;
        }

        public Car(int id, String plate, double dailyPrise, int modelYear, double engine, String bodyType, int doors, String transmission, String driveTrain, Boolean isAvailable, String fuelType, String link, Date rentEndDate, Model model) {
                this.id = id;
                this.plate = plate;
                this.dailyPrise = dailyPrise;
                this.modelYear = modelYear;
                this.engine = engine;
                this.bodyType = bodyType;
                this.doors = doors;
                this.transmission = transmission;
                this.driveTrain = driveTrain;
                this.isAvailable = isAvailable;
                this.fuelType = fuelType;
                this.link = link;
                this.rentEndDate = rentEndDate;
                this.model = model;
        }
}
