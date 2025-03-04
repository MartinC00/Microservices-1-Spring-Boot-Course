package com.udemy.microservices1.contact_list.DAO;

import com.udemy.microservices1.contact_list.model.Contact;

import java.util.List;

public interface iContactListDAO {
    Contact addContact(Contact contact);
    Contact getContact(String email);
    void deleteContact(String email);
    List<Contact> getContactListJPA();
    void deleteContact(int idContact);
    Contact getContact(int idContact);
    Contact updateContact(Contact contact);
}