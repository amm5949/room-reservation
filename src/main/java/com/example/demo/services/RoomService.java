package com.example.demo.services;

import com.example.demo.dtos.RoomDto;
import com.example.demo.exception.CustomNotFoundException;
import com.example.demo.models.Room;
import com.example.demo.models.enums.RoomStatus;
import com.example.demo.repositories.IRoomRepository;
import com.example.demo.services.interfaces.IRoomService;
import com.example.demo.vms.RoomVM;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService implements IRoomService {

    private final IRoomRepository roomRepository;

    public RoomService(IRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @Override
    public List<RoomVM> getRooms() {
        return roomRepository.findAll().stream().map(x -> entityToVM(x)).collect(Collectors.toList());
    }

    @Override
    public List<RoomVM> getAvailableRooms() {
        return roomRepository.findAll().stream().filter(x -> x.getStatus() == RoomStatus.Available).map(x -> entityToVM(x)).collect(Collectors.toList());
    }

    @Override
    public List<RoomVM> getUnavailableRooms() {
        return roomRepository.findAll().stream().filter(x -> x.getStatus() != RoomStatus.Available).map(x -> entityToVM(x)).collect(Collectors.toList());
    }

    @Override
    public RoomVM getRoomById(long id) {
        return entityToVM(roomRepository.findById(id).orElseThrow(() -> new CustomNotFoundException("Room", id)));
    }

    @Override
    public RoomVM createRoom(RoomDto room) {
        return entityToVM(roomRepository.save(dtoToEntity(room)));
    }

    @Override
    public RoomVM updateRoom(RoomDto room, long id) {
        Room room1 = roomRepository.findById(id).orElseThrow(() -> new CustomNotFoundException("Room", id));
        room1.setTitle(room.getTitle());
        room1.setStatus(room.getStatus());
        room1.setCapacity(room.getCapacity());
        room1.setPrice(room.getPrice());
        return entityToVM(roomRepository.save(room1));
    }

    @Override
    public void deleteRoomById(long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new CustomNotFoundException("Room", id));
        roomRepository.delete(room);
    }

    private Room dtoToEntity(RoomDto roomDto) {
        Room room = new Room();
        room.setTitle(roomDto.getTitle());
        room.setCapacity(roomDto.getCapacity());
        room.setPrice(roomDto.getPrice());
        room.setStatus(roomDto.getStatus());
        room.setImageUrl(roomDto.getImageUrl());
        return room;
    }

    private RoomVM entityToVM(Room room) {
        RoomVM roomVM = new RoomVM();
        roomVM.setId(room.getId());
        roomVM.setTitle(room.getTitle());
        roomVM.setCapacity(room.getCapacity());
        roomVM.setPrice(room.getPrice());
        roomVM.setImageUrl(room.getImageUrl());
        roomVM.setStatus(room.getStatus());
        return roomVM;
    }
}
