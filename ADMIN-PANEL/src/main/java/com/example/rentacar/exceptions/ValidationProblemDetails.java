package com.example.rentacar.exceptions;


import java.util.Map;


public class ValidationProblemDetails extends ProblemDetails{
   private Map<String,String> validationErrors;

   public Map<String, String> getValidationErrors() {
      return validationErrors;
   }

   public void setValidationErrors(Map<String, String> validationErrors) {
      this.validationErrors = validationErrors;
   }


}
