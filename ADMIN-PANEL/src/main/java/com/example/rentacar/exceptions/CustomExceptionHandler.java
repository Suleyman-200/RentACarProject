package com.example.rentacar.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ProblemDetails handleBusinessException(BusinessException businessException) {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setMessage(businessException.getMessage());
        return problemDetails;
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
        validationProblemDetails.setMessage("VALIDATION EXCEPTION!!!!");
        validationProblemDetails.setValidationErrors(new HashMap<>());
        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            validationProblemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return validationProblemDetails;
    }

    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ExceptionResponseFeign exceptionResponseFeign(UndeclaredThrowableException exception) {
        log.error("UndeclaredThrowable exception", exception);
        return new ExceptionResponseFeign(HttpStatus.SERVICE_UNAVAILABLE.name(), exception.getCause().getCause().getMessage());
    }
}
