package com.example.demo.controllers;

import com.example.demo.dtos.RoomDto;
import com.example.demo.models.Room;
import com.example.demo.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/rooms")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @GetMapping()
    public ResponseEntity<List<RoomDto>> allRooms() {
        return new ResponseEntity<>(managerService.showAllRooms(), HttpStatus.OK);
    }

    @PostMapping("/accept/{roomId}")
    public ResponseEntity<Room> acceptRoomReq(@PathVariable long roomId) {
        return new ResponseEntity<>(managerService.acceptRoomById(roomId), HttpStatus.OK);
    }

    @PostMapping("/decline/{roomId}")
    public ResponseEntity<Room> declineRoomReq(@PathVariable long roomId) {
        return new ResponseEntity<>(managerService.declineRoomById(roomId), HttpStatus.OK);
    }
}
