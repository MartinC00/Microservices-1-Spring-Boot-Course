package com.udemy.microservices1.contact_list.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionGlobalHandler {
    @ExceptionHandler(ContactAlreadyExistsException.class)
    public ResponseEntity<String> handleContactAlreadyExistsException(ContactAlreadyExistsException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

}
