package com.example.userpanel.dto.request;

import javax.validation.constraints.NotNull;

public class FeedBackRequest {
    @NotNull
    private String userName;
    @NotNull
    private String message;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
