package com.udemy.microservices1.contact_list.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ContactAlreadyExistsException extends RuntimeException{
    public ContactAlreadyExistsException(String email){
        super("A contact with this email: '"+email+"' already exists");
    }
}
