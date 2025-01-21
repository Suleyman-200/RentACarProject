package com.example.rentacar.dto.request;

import javax.validation.constraints.NotNull;

public class AboutUsRequest {
    @NotNull

    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
