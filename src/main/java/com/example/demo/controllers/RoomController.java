package com.example.demo.controllers;

import com.example.demo.dtos.RoomDto;
import com.example.demo.services.RoomService;
import com.example.demo.vms.RoomVM;
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
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    @Operation(summary = "Get all Rooms", description = "Retrieve a list of all Rooms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of rooms",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoomDto.class))),
            @ApiResponse(responseCode = "404", description = "No Room found")
    })
    public ResponseEntity<List<RoomVM>> getAllRooms() {
        return new ResponseEntity<>(roomService.getRooms(), HttpStatus.OK);
    }

    @GetMapping("/available")
    @Operation(summary = "Get all available", description = "Retrieve a list of all available Room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of available rooms",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoomDto.class))),
            @ApiResponse(responseCode = "404", description = "No available room found")
    })
    public ResponseEntity<List<RoomVM>> getAllAvailableRooms() {
        return new ResponseEntity<>(roomService.getAvailableRooms(), HttpStatus.OK);
    }

    @GetMapping("/unavailable")
    @Operation(summary = "Get all unavailable", description = "Retrieve a list of all unavailable Room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of unavailable rooms",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoomDto.class))),
            @ApiResponse(responseCode = "404", description = "No unavailable room found")
    })
    public ResponseEntity<List<RoomVM>> getAllUnavailableRooms() {
        return new ResponseEntity<>(roomService.getUnavailableRooms(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get room by Id", description = "Retrieve a Room by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved room",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoomDto.class))),
            @ApiResponse(responseCode = "404", description = "No room found with this Id")
    })
    public ResponseEntity<RoomVM> getRoomById(@PathVariable Long id) {
        return new ResponseEntity<>(roomService.getRoomById(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Get room by Id", description = "Retrieve a Room by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved room",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoomDto.class))),
            @ApiResponse(responseCode = "404", description = "No room found with this Id")
    })
    public ResponseEntity<RoomVM> createRoom(@RequestBody RoomDto roomDto) {
        return new ResponseEntity<>(roomService.createRoom(roomDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update room", description = "Update room from DTO model and convert to room entity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated room",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoomDto.class))),
            @ApiResponse(responseCode = "404", description = "No room found with this Id")
    })
    public ResponseEntity<RoomVM> updateRoom(@RequestBody RoomDto roomDto, @PathVariable long id) {
        return new ResponseEntity<>(roomService.updateRoom(roomDto, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete room", description = "Delete room by Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted room",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoomDto.class))),
            @ApiResponse(responseCode = "404", description = "No room found with this Id")
    })
    public ResponseEntity<?> deleteRoom(@PathVariable long id) {
        roomService.deleteRoomById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
