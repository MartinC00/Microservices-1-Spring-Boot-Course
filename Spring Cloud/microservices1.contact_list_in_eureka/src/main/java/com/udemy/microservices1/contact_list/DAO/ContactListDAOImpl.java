package com.udemy.microservices1.contact_list.DAO;

import com.udemy.microservices1.contact_list.model.Contact;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContactListDAOImpl implements iContactListDAO {

    private static final Logger log = LoggerFactory.getLogger(ContactListDAOImpl.class);
    private final iContactListJPA contactListJPA;

    @Override
    public Contact addContact(Contact contact) {
        return contactListJPA.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        return contactListJPA.save(contact);
    }

    @Override
    public List<Contact> getContactListJPA() {
        simulateDelay(); //useful for async tests
        return contactListJPA.findAll();
    }

    private void simulateDelay(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Contact getContact(int idContact) {
        return contactListJPA.findById(idContact).orElse(null);
    }

    @Override
    public Contact getContact(String email) {
        return contactListJPA.findByEmail(email);
    }

    @Override
    public void deleteContact(String email) {
        contactListJPA.deleteForEmail(email);
    }

    @Override
    public void deleteContact(int idContact) {
        contactListJPA.deleteById(idContact);
    }
}
