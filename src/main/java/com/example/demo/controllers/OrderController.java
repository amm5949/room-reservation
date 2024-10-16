package com.example.demo.controllers;

import com.example.demo.dtos.OrderDto;
import com.example.demo.services.EmailService;
import com.example.demo.services.OrderService;
import com.example.demo.vms.OrderVM;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all orders", description = "Retrieve a list of all Orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of orders",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(responseCode = "404", description = "No Order found")
    })
    public ResponseEntity<List<OrderVM>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/accepted")
    @Operation(summary = "Get all accepted order", description = "Retrieve a list of all Order has been accepted by admin or manager")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of accepted order",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(responseCode = "404", description = "Accepted order list is empty!")
    })
    public ResponseEntity<List<OrderVM>> getAcceptedOrders() {
        return new ResponseEntity<>(orderService.getAcceptedOrders(), HttpStatus.OK);
    }

    @GetMapping("/rejected")
    @Operation(summary = "Get all declined order", description = "Retrieve a list of all Order has been declined by admin or manager")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of declined order",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(responseCode = "404", description = "Declined order list is empty!")
    })
    public ResponseEntity<List<OrderVM>> getRejectedOrders() {
        return new ResponseEntity<>(orderService.getRejectedOrders(), HttpStatus.OK);
    }

    @GetMapping("/pending")
    @Operation(summary = "Get all pending order", description = "Retrieve a list of all Orders in Pending status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of accepted order",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(responseCode = "404", description = "Accepted order list is empty!")
    })
    public ResponseEntity<List<OrderVM>> getPendingOrders() {
        return new ResponseEntity<>(orderService.getPendingOrders(), HttpStatus.OK);
    }

    @GetMapping("{username}")
    @Operation(summary = "Get order by user-name", description = "Retrieve a list of all user orders by UserName.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved User orders list",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(responseCode = "404", description = "Accepted order list is empty!")
    })
    public ResponseEntity<List<OrderVM>> getOrderById(@PathVariable String username) {
        return new ResponseEntity<>(orderService.getOrdersByClientName(username), HttpStatus.OK);
    }

    @PostMapping("/{roomId}")
    @Operation(summary = "Create order", description = "Create an Order by using the Post method.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created an Order",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))),
    })
    public ResponseEntity<OrderVM> createOrder(@PathVariable Long roomId) {
        return new ResponseEntity<>(orderService.makeOrder(roomId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/accept")
    @Operation(summary = "Accept order by ID", description = "Update an Order status to Accepted by Order ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated successfully to Accept status",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(responseCode = "404", description = "order not found!")
    })
    public ResponseEntity<OrderVM> acceptOrder(@PathVariable Long id) {
        ResponseEntity<OrderVM> response = new ResponseEntity<>(orderService.acceptOrder(id), HttpStatus.OK);

        if (orderService.checkOrderStats(id)) {

            emailService.sendMail(userService.getLoggedInEmail()
                    , "Room request"
                    , "Your reservation request successfully accepted!");
        }
        return response;
    }

    @PutMapping("{id}/decline")
    @Operation(summary = "Reject order by ID", description = "Update an Order status to Rejected by Order ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated successfully to Rejected status",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(responseCode = "404", description = "order not found!")
    })
    public ResponseEntity<OrderVM> rejectOrder(@PathVariable Long id) {
        ResponseEntity<OrderVM> response = new ResponseEntity<>(orderService.rejectOrder(id), HttpStatus.OK);

        if (orderService.checkOrderStats(id)) {

            emailService.sendMail(userService.getLoggedInEmail()
                    , "Room request"
                    , "Your reservation request unfortunately rejected (:");
        }
        return response;

    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete order by ID", description = "Delete an Order by Order ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order deleted successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(responseCode = "404", description = "order not found!")
    })
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
