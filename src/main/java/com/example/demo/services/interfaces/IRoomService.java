package com.example.demo.services.interfaces;

import com.example.demo.dtos.RoomDto;
import com.example.demo.models.Room;
import com.example.demo.vms.RoomVM;

import java.util.List;

public interface IRoomService {
    List<RoomVM> getRooms();

    List<RoomVM> getAvailableRooms();

    List<RoomVM> getUnavailableRooms();

    RoomVM getRoomById(long id);

    RoomVM createRoom(RoomDto room);

    RoomVM updateRoom(RoomDto room, long id);

    void deleteRoomById(long id);

}
