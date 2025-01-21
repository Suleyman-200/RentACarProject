package com.example.rentacar.business.rules;

import com.example.rentacar.dto.responses.MessageResponse;
import com.example.rentacar.repositories.UserRepository;
import com.example.rentacar.exceptions.BusinessException;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Service
public class UserBusinessRules {
    private final UserRepository userRepository;

    public UserBusinessRules(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public void checkIfexistsCustomerByEmail(String email) {
        if (this.userRepository.existsUserByeMail(email)) {
            new MessageResponse("Email already used");
        }
    }

    public boolean checkIfexistsCustomerByNumber(String phone) {
        if (this.userRepository.existsUserByPhone(phone)) {
            throw new BusinessException("Number already used");
        }

        return false;
    }
    private static final int AGELIMIT=20;
    public boolean isAgeAllowed(@NotNull LocalDate birthdate){
        int age=LocalDate.now().getYear() -birthdate.getYear();
        if (AGELIMIT>age){
            throw new BusinessException("U are little");

        }
        return false;
    }
}
