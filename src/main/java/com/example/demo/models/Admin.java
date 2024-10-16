package com.example.demo.models;

import lombok.AllArgsConstructor;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
public class Admin extends User {

    public Admin(String username, String password, String email) {
        super(username, password, email);
    }
}
