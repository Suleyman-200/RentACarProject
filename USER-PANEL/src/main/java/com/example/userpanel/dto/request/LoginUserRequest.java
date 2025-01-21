package com.example.userpanel.dto.request;


import javax.validation.constraints.NotNull;

public class LoginUserRequest {

    @NotNull
    String userName;
    @NotNull
    String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
