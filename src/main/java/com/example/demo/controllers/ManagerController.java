package com.example.demo.controllers;

import com.example.demo.dtos.OrderDto;
import com.example.demo.dtos.RoomDto;
import com.example.demo.models.Manager;
import com.example.demo.models.Room;
import com.example.demo.services.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get all manager", description = "Retrieve a list of all managers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of managers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Manager.class))),
            @ApiResponse(responseCode = "404", description = "No Manager found")
    })
    public ResponseEntity<List<Manager>> getAllManager() {
        return new ResponseEntity<>(managerService.getAllManagers(), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @Operation(summary = "Update manager information", description = "Update the information of a manager by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated manager info",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Manager.class))),
            @ApiResponse(responseCode = "400", description = "Invalid manager ID"),
            @ApiResponse(responseCode = "404", description = "Manager not found")
    })
    public ResponseEntity<Manager> updateManager(@RequestBody Manager manager, @PathVariable long id) {
        return new ResponseEntity<>(managerService.updateManager(manager, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete manager by ID", description = "Delete a manager from the system by their ID")
    @ApiResponse(responseCode = "200", description = "Manager deleted successfully")
    @ApiResponse(responseCode = "404", description = "Manager not found")
    public ResponseEntity<String> deleteOrderById(@PathVariable long id) {
        managerService.deleteManager(id);
        return new ResponseEntity<>("Manager deleted successfully!", HttpStatus.OK);
    }
}
