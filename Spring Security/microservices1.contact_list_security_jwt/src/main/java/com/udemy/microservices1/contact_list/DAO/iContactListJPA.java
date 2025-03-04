package com.udemy.microservices1.contact_list.DAO;

import com.udemy.microservices1.contact_list.model.Contact;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface iContactListJPA extends JpaRepository<Contact, Integer> {
    Contact findByEmail(String email);

    @Transactional
    @Modifying
    @Query("DELETE from Contact c WHERE c.email=?1")
    void deleteForEmail(String email);
}
