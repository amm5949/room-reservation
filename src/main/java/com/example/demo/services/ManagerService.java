package com.example.demo.services;

import com.example.demo.dtos.RoomDto;
import com.example.demo.models.Room;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.services.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ManagerService implements IManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    RoomService roomService;

    @Override
    public List<RoomDto> showAllRooms() {
        return roomService.getRooms();
    }

    @Override
    public Room acceptRoomById(long roomId) {
        return roomService.acceptRoom(roomId);
    }

    @Override
    public RoomDto declineRoomById(long roomId) {
        return null;
    }
}
