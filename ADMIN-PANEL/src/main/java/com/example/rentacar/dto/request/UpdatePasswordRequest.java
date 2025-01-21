package com.example.rentacar.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdatePasswordRequest {
    @NotNull
    @Size(min = 3,max = 30)
    String userName;
    @NotNull
    @Email
    String eMail;
    @NotNull

    String phone;

    @NotNull
    @Size(min = 5,max = 30)
    String password;

    @NotNull
    @Size(min = 5,max = 30)
    String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = userName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
