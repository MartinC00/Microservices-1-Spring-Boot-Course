package com.udemy.microservices1.contact_list.controller;

import com.udemy.microservices1.contact_list.model.Contact;
import com.udemy.microservices1.contact_list.service.iContactListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "contact list CRUD methods")
public class ContactListController {

    private final iContactListService contactListService;

    @GetMapping
    @ApiOperation(value = "return the whole contact list")
    public List<Contact> getContactList(){
        return contactListService.getAllContacts();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "return a specific contact using its id")
    public ResponseEntity<Contact> getContact(@ApiParam(value = "Contact identifier", required = true)
                                                  @PathVariable("id") int id){
        Contact contact = contactListService.findContact(id);
        if(!Objects.isNull(contact)) return ResponseEntity.ok(contact);
        else return ResponseEntity.notFound().build();
    }
    @PostMapping
    @ApiOperation(value = "save a new contact and return it")
    public ResponseEntity<Contact> createNewContact(@ApiParam(value = "Contact object received in json")
                                                        @RequestBody Contact contact){
        return ResponseEntity.ok(contactListService.addContact(contact));
    }
    @PutMapping
    @ApiOperation(value = "update an existing contact and return it")
    public ResponseEntity<Contact> updateContact(@ApiParam(value = "Contact object received in json")
                                                     @RequestBody Contact contact){
        return ResponseEntity.ok(contactListService.updateContact(contact));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete an existing contact using its id")
    public ResponseEntity<Boolean> deleteContact(@ApiParam(value = "Contact identifier", required = true)
                                                     @PathVariable("id") int id){
        return ResponseEntity.ok(contactListService.deleteContact(id));
    }


}
