package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Client extends User
{
    private Integer balance;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;
}
