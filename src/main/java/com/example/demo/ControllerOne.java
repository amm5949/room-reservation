package com.example.demo;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerOne {


    @GetMapping("myTest")
    @Operation(summary = "myTest")
    public String myTestGet(@RequestParam String name) {
        return "hello World " + name + "!";
    }
}
