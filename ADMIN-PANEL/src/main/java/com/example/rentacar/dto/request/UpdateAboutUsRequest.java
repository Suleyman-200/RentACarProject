package com.example.rentacar.dto.request;

import javax.validation.constraints.NotNull;

public class UpdateAboutUsRequest {
    @NotNull
    Long id;
    @NotNull
    String content;

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
