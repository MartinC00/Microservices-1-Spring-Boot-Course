package com.udemy.microservices1.contact_list.service;

import com.udemy.microservices1.contact_list.DAO.iContactListDAO;
import com.udemy.microservices1.contact_list.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactListServiceImpl implements iContactListService{

    @Autowired
    private iContactListDAO contactDAO;

    @Override
    public Contact addContact(Contact contact) {
        return contactDAO.addContact(contact);
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactDAO.getContactListJPA();
    }

    @Override
    public Contact updateContact(Contact contact) {
        return contactDAO.updateContact(contact);
    }

    @Override
    public boolean deleteContact(int idContact) {
        if(contactExists(idContact)) {
            contactDAO.deleteContact(idContact);
            return true;
        }
        return false;
    }

    @Override
    public Contact findContact(int idContact) {
        return contactDAO.getContact(idContact);
    }

    private boolean contactExists(int idContact){
        return contactDAO.getContact(idContact) != null;
    }
}
