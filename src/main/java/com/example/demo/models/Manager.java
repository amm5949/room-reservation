package com.example.demo.models;

import lombok.AllArgsConstructor;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
public class Manager extends User{

    public Manager(String username, String password, String email) {
        super(username, password, email);
    }
}
