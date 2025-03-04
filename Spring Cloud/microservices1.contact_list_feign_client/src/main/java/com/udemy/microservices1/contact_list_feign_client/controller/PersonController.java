package com.udemy.microservices1.contact_list_feign_client.controller;

import com.udemy.microservices1.contact_list_feign_client.model.FeignPerson;
import com.udemy.microservices1.contact_list_feign_client.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class PersonController {

    private final FeignPerson feignPerson;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> addPerson(@RequestBody Person person){
        feignPerson.callForNewContact(person);
        return feignPerson.callForContactList();
    }

    @GetMapping
    public List<Person> getAllClients(){
        return feignPerson.callForContactList();

    }
}
