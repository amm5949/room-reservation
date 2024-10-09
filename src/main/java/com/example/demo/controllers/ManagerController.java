package com.example.demo.controllers;

import com.example.demo.dtos.OrderDto;
import com.example.demo.dtos.RoomDto;
import com.example.demo.models.Room;
import com.example.demo.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;


    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllRooms() {
        return new ResponseEntity<>(managerService.getOrderLists(), HttpStatus.OK);
    }

    @PostMapping("/{id}/accpet")
    public ResponseEntity<OrderDto> acceptOrderRequest(@PathVariable long id) {
        return new ResponseEntity<>(managerService.acceptOrder(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/decline")
    public ResponseEntity<OrderDto> declineOrderRequest(@PathVariable long id) {
        return new ResponseEntity<>(managerService.declienOrder(id), HttpStatus.OK);
    }
}
