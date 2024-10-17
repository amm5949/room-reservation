package com.example.demo;


import com.example.demo.init.Data;
import com.example.demo.models.*;
import com.example.demo.models.enums.OrderStatus;
import com.example.demo.models.enums.RoomStatus;
import com.example.demo.repositories.IClientRepository;
import com.example.demo.repositories.IOrderRepository;
import com.example.demo.repositories.IRoomRepository;
import com.example.demo.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private Data data;

    @PostConstruct
    public void init() {
        data.SeedDatabase();
    }
}
