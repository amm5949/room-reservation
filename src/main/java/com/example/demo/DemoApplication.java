package com.example.demo;

import com.example.demo.models.Admin;
import com.example.demo.models.Client;
import com.example.demo.models.Manager;
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
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        userRepository.save(new Admin("admin", passwordEncoder.encode("admin"), "admin@email.com"));
        userRepository.save(new Manager("manager", passwordEncoder.encode("manager"), "manager@email.com"));
        userRepository.save(new Client("client", passwordEncoder.encode("client"), "client@email.com"));
    }
}
