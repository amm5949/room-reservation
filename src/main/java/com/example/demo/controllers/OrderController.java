package com.example.demo.controllers;

import com.example.demo.dtos.OrderDto;
import com.example.demo.services.OrderService;
import com.example.demo.vms.OrderVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderVM>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/accepted")
    public ResponseEntity<List<OrderVM>> getAcceptedOrders() {
        return new ResponseEntity<>(orderService.getAcceptedOrders(), HttpStatus.OK);
    }
    @GetMapping("/rejected")
    public ResponseEntity<List<OrderVM>> getRejectedOrders() {
        return new ResponseEntity<>(orderService.getRejectedOrders(), HttpStatus.OK);
    }
    @GetMapping("/pending")
    public ResponseEntity<List<OrderVM>> getPendingOrders() {
        return new ResponseEntity<>(orderService.getPendingOrders(), HttpStatus.OK);
    }

    @GetMapping("{username}")
    public ResponseEntity<List<OrderVM>> getOrderById(@PathVariable String username) {
        return new ResponseEntity<>(orderService.getOrdersByClientName(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderVM> createOrder(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.makeOrder(orderDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<OrderVM> acceptOrder(@PathVariable Long id){
        return new ResponseEntity<>(orderService.acceptOrder(id), HttpStatus.OK);
    }
    @PutMapping("{id}/decline")
    public ResponseEntity<OrderVM> rejectOrder(@PathVariable Long id){
        return new ResponseEntity<>(orderService.rejectOrder(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
