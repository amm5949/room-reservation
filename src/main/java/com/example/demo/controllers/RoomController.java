package com.example.demo.controllers;

import com.example.demo.dtos.RoomDto;
import com.example.demo.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController
{
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRooms(){
        return new ResponseEntity<>(roomService.getRooms(), HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<RoomDto>> getAllAvailableRooms(){
        return new ResponseEntity<>(roomService.getAvailableRooms(), HttpStatus.OK);
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<RoomDto>> getAllUnavailableRooms(){
        return new ResponseEntity<>(roomService.getUnavailableRooms(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long id){
        return new ResponseEntity<>(roomService.getRoomById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomDto roomDto){
        return new ResponseEntity<>(roomService.createRoom(roomDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<RoomDto> updateRoom(@RequestBody RoomDto roomDto, @PathVariable long id){
        return new ResponseEntity<>(roomService.updateRoom(roomDto, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RoomDto> deleteRoom(@PathVariable long id){
        roomService.deleteRoomById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
