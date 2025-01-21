package com.example.rentacar.dto.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UpdateUserRequest {

    Long id;

    @NotNull
    @Email
    private String eMail;
    @NotNull
    private String phone;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return eMail;
    }

    public void setEmail(String email) {
        this.eMail = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
