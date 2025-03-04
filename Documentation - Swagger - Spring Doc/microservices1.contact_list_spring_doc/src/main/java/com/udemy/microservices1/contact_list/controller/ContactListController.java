package com.udemy.microservices1.contact_list.controller;

import com.udemy.microservices1.contact_list.model.Contact;
import com.udemy.microservices1.contact_list.service.iContactListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("contactList")
@RequiredArgsConstructor
@Tag(name = "ContactList", description = "API for contact list CRUD methods")
public class ContactListController {

    private final iContactListService contactListService;

    @GetMapping
    @Operation(summary = "contact list", description = "return the whole contact list")
    public List<Contact> getContactList(){
        return contactListService.getAllContacts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "GET contact", description = "return a specific contact using its id")
    public ResponseEntity<Contact> getContact(@Parameter(description = "Contact identifier", required = true)
                                                  @PathVariable("id") int id){
        Contact contact = contactListService.findContact(id);
        if(!Objects.isNull(contact)) return ResponseEntity.ok(contact);
        else return ResponseEntity.notFound().build();
    }
    @PostMapping
    @Operation(summary = "ADD contact", description = "save a new contact and return it")
    public ResponseEntity<Contact> createNewContact(@Parameter(description = "Contact object received in json")
                                                        @RequestBody Contact contact){
        return ResponseEntity.ok(contactListService.addContact(contact));
    }
    @PutMapping
    @Operation(summary = "UPDATE contact", description = "update an existing contact and return it")
    public ResponseEntity<Contact> updateContact(@Parameter(description = "Contact object received in json")
                                                     @RequestBody Contact contact){
        return ResponseEntity.ok(contactListService.updateContact(contact));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "DELETE contact", description = "delete an existing contact using its id")
    public ResponseEntity<Boolean> deleteContact(@Parameter(description = "Contact identifier", required = true)
                                                     @PathVariable("id") int id){
        return ResponseEntity.ok(contactListService.deleteContact(id));
    }


}
