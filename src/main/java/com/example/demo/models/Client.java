package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client extends Person{

    private int balance;

    public Client(String username, String password, int balance) {
        super(username, password);
        this.balance = balance;
    }
}
