package com.udemy.microservices1.contact_list_feign_client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Person {
    private String name;
    private String email;
    private int age;
}
