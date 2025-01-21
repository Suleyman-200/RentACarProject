package com.example.rentacar.business.concretes;

import com.example.rentacar.dto.request.ContactDTO;
import com.example.rentacar.exceptions.NotFoundException;
import com.example.rentacar.models.Contact;

import com.example.rentacar.repositories.ContactUsRepository;


import com.example.rentacar.mappers.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContactUsManager {
    private final ContactUsRepository contactUsRepository;
    private final ModelMapperService modelMapperService;


    public ContactUsManager(ContactUsRepository contactUsRepository, ModelMapperService modelMapperService) {
        this.contactUsRepository = contactUsRepository;
        this.modelMapperService = modelMapperService;
    }
    public List<ContactDTO> getAll() {
        List<Contact> contactUsList = contactUsRepository.findAll();
        return contactUsList.stream()
                .map(contact -> modelMapperService.forResponse().map(contact, ContactDTO.class))
                .toList();
    }
    public void saveContact(ContactDTO contactDTO) {
        Contact contact = modelMapperService.forRequest().map(contactDTO, Contact.class);
        contactUsRepository.save(contact);
    }
    public void uptadeContact(ContactDTO contactDTO) {
        Contact existingContact = contactUsRepository.findById(contactDTO.getId()).orElseThrow(()->new NotFoundException("Not found this id"));
        if (existingContact != null) {
            modelMapperService.forRequest().map(contactDTO, existingContact);
            contactUsRepository.save(existingContact);
        }
    }
        public void deleteContact(Long id) {
        Contact contact=contactUsRepository.findById(id).orElseThrow(()->new NotFoundException("Not found this id"));
       this.contactUsRepository.delete(contact);
        }

    }




