package com.example.rentacar.models;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity

@Table(name = "rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "car_plate", referencedColumnName = "plate")
    private Car car;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "rent_start_date")
    private Date rentStartDate;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "rent_end_date")
    private Date rentEndDate;

    public Rent() {
    }

    public Rent(Long id, Car car, User user, Date rentStartDate, Date rentEndDate) {
        this.id = id;
        this.car = car;
        this.user = user;
        this.rentStartDate = rentStartDate;
        this.rentEndDate = rentEndDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

