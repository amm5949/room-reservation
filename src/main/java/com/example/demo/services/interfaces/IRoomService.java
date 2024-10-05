package com.example.demo.services.interfaces;

import com.example.demo.dtos.RoomDto;
import com.example.demo.models.Room;

import java.util.List;

public interface IRoomService {
    List<RoomDto> getRooms();

    List<RoomDto> getAvailableRooms();

    List<RoomDto> getUnavailableRooms();

    RoomDto getRoomById(long id);

    RoomDto createRoom(RoomDto room);

    RoomDto updateRoom(RoomDto room, long id);

    void deleteRoomById(long id);

    Room acceptRoom(long roomId);

    Room declineRoom(long roomId);
}
