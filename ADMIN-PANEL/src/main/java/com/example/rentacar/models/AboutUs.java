package com.example.rentacar.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AboutUs")
public class AboutUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    @NotNull

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
