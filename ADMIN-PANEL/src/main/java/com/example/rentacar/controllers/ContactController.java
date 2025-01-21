package com.example.rentacar.controllers;

import com.example.rentacar.business.concretes.ContactUsManager;
import com.example.rentacar.dto.request.ContactDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactUsManager contactService;

    public ContactController(ContactUsManager contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        List<ContactDTO> contacts = contactService.getAll();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addContact(@RequestBody ContactDTO contactDTO) {
        contactService.saveContact(contactDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateContact(@RequestBody ContactDTO contactDTO) {
        contactService.uptadeContact(contactDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
