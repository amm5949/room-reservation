package com.example.demo.controllers;

import com.example.demo.dtos.OrderDto;
import com.example.demo.dtos.RoomDto;
import com.example.demo.models.Manager;
import com.example.demo.models.Room;
import com.example.demo.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/managers")
public class ManagerController {
    @Autowired
    ManagerService managerService;


    @GetMapping
    public ResponseEntity<List<Manager>> getAllManager() {
        return new ResponseEntity<>(managerService.getAllManagers(), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Manager> updateManager(@RequestBody Manager manager, @PathVariable long id) {
        return new ResponseEntity<>(managerService.updateManager(manager, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> declineOrderRequest(@PathVariable long id) {
        managerService.deleteManager(id);
        return ResponseEntity.noContent().build();
    }
}
