package com.udemy.microservices1.contact_list.service;

import com.udemy.microservices1.contact_list.model.Contact;

import java.util.List;
public interface iContactListService {
    Contact addContact(Contact contact);
    List<Contact> getAllContacts();
    Contact updateContact(Contact contact);
    boolean deleteContact(int idContact);
    Contact findContact(int idContact);
}
