package com.example.demo.controllers;

import com.example.demo.dtos.OrderDto;
import com.example.demo.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/accepted")
    public ResponseEntity<List<OrderDto>> getAcceptedOrders() {
        return new ResponseEntity<>(orderService.getAcceptedOrders(), HttpStatus.OK);
    }
    @GetMapping("/rejected")
    public ResponseEntity<List<OrderDto>> getRejectedOrders() {
        return new ResponseEntity<>(orderService.getRejectedOrders(), HttpStatus.OK);
    }
    @GetMapping("/pending")
    public ResponseEntity<List<OrderDto>> getPendingOrders() {
        return new ResponseEntity<>(orderService.getPendingOrders(), HttpStatus.OK);
    }

    @GetMapping("{username}")
    public ResponseEntity<List<OrderDto>> getOrderById(@PathVariable String username) {
        return new ResponseEntity<>(orderService.getOrdersByClientName(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.makeOrder(orderDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<OrderDto> acceptOrder(@PathVariable Long id){
        return new ResponseEntity<>(orderService.acceptOrder(id), HttpStatus.OK);
    }
    @PutMapping("{id}/reject")
    public ResponseEntity<OrderDto> rejectOrder(@PathVariable Long id){
        return new ResponseEntity<>(orderService.rejectOrder(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<OrderDto> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
