package com.example.rentacar.repositories;


import com.example.rentacar.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByuserName(String userName);
    Optional<User> findByeMailAndPhone(String eMail, String phone);
    User findByeMail(String eMail);



    boolean existsUserByeMail(String eMail);
    boolean existsUserByuserName(String userName);
    boolean existsUserByPhone(String phone);










}
