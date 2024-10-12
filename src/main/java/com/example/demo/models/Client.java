package com.example.demo.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client extends User
{

    public Client(String username, String password){
        super(username, password);
    }

    private Integer balance;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

}
