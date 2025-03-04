package com.udemy.microservices1.contact_list.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="contacts")
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContact;
    private String name;
    private String email;
    private int age;
}
