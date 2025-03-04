package com.udemy.microservices1.contact_list_feign_client.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value="contact-list-service/contactList")
public interface FeignPerson {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<Person> callForContactList();

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    Person callForNewContact(@RequestBody Person person);
}
