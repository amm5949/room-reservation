package com.example.demo.services;

import com.example.demo.dtos.RoomDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.Room;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.services.interfaces.IManagerService;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService implements IManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    RoomService roomService;

    @Override
    public List<RoomDto> showAllRooms() {
        List<RoomDto> roomDtoList = roomService.getRooms();

        if (roomDtoList.isEmpty()) {
            throw new NotFoundException("There is no Room to show!");
        }
        return roomDtoList;
    }

    @Override
    public Room acceptRoomById(long roomId) {
        // not found exception in room service
        return roomService.acceptRoom(roomId);
    }

    @Override
    public Room declineRoomById(long roomId) {
        // not found exception in room service
        return roomService.declineRoom(roomId);
    }
}
