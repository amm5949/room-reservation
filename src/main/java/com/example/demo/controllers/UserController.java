package com.example.demo.controllers;

import com.example.demo.dtos.AuthDto;
import com.example.demo.services.UserService;
import com.example.demo.vms.AuthVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthVM> register(@RequestBody AuthDto authDto) {
        return new ResponseEntity<>(userService.register(authDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthVM> login(@RequestBody AuthDto authDto) {
        return new ResponseEntity<>(userService.login(authDto), HttpStatus.OK);
    }
}
