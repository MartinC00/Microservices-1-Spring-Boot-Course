package com.udemy.microservices1.contact_list.controller;

import com.udemy.microservices1.contact_list.model.Contact;
import com.udemy.microservices1.contact_list.service.iContactListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("contactList")
@RequiredArgsConstructor
public class ContactListController {

    private final iContactListService contactListService;

    @GetMapping
    public List<Contact> getContactList(){
        return contactListService.getAllContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable("id") int id){
        Contact contact = contactListService.findContact(id);
        if(!Objects.isNull(contact)) return ResponseEntity.ok(contact);
        else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Contact> createNewContact(@RequestBody Contact contact){
        return new ResponseEntity<>(contactListService.addContact(contact), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact){
        return ResponseEntity.ok(contactListService.updateContact(contact));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteContact(@PathVariable("id") int id){ return ResponseEntity.ok(contactListService.deleteContact(id)); }


}
