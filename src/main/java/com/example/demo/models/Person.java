package com.example.demo.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Person {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static int idInc;
    private int personId;
    private String username;
    private String password;

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
        setPersonId();
    }

    private void setPersonId() {
        this.personId = idInc++;
    }
}
