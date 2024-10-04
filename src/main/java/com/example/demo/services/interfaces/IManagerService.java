package com.example.demo.services.interfaces;

import com.example.demo.dtos.RoomDto;
import com.example.demo.models.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IManagerService {

    List<RoomDto> showAllRooms();

    Room acceptRoomById(long roomId);

    RoomDto declineRoomById(long roomId);

}
