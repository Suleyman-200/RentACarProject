package com.example.rentacar.models;




import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.List;

@Entity
@Table(name = "brands")

@JsonIgnoreProperties({"models"})
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull
    private String name;

    @OneToMany(mappedBy = "brand", cascade =CascadeType.PERSIST,orphanRemoval = true)
    private List<Model> models;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public Brand() {
    }

    public Brand(int id, String name, List<Model> models) {
        this.id = id;
        this.name = name;
        this.models = models;
    }
}
