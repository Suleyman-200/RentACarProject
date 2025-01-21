package com.example.userpanel.dto.response;

import java.util.List;


    public class ContactDTO {
        private String email;
        private List<String> phoneNumbers;



        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List<String> getPhoneNumbers() {
            return phoneNumbers;
        }

        public void setPhoneNumbers(List<String> phoneNumbers) {
            this.phoneNumbers = phoneNumbers;
        }
    }

