package com.example.rentacar.repositories;


import com.example.rentacar.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactUsRepository extends JpaRepository<Contact,Long> {

}
