package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password);
    }
}
