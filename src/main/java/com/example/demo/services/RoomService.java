package com.example.demo.services;

import com.example.demo.dtos.RoomDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.Room;
import com.example.demo.models.enums.RoomAcceptance;
import com.example.demo.models.enums.RoomStatus;
import com.example.demo.repositories.IRoomRepository;
import com.example.demo.services.interfaces.IRoomService;
import jdk.jfr.internal.tool.PrettyWriter;
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
    public List<RoomDto> getRooms() {
        return roomRepository.findAll().stream().map(x -> toDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getAvailableRooms() {
        return roomRepository.findAll().stream().filter(x -> x.getStatus() == RoomStatus.Available).map(x -> toDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getUnavailableRooms() {
        return roomRepository.findAll().stream().filter(x -> x.getStatus() != RoomStatus.Available).map(x -> toDto(x)).collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(long id) {
        return toDto(roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room", id)));
    }

    @Override
    public RoomDto createRoom(RoomDto room) {
        return toDto(roomRepository.save(toEntity(room)));
    }

    @Override
    public RoomDto updateRoom(RoomDto room, long id) {
        Room room1 = roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room", id));
        room1.setRoomNumber(room.getRoomNumber());
        room1.setStatus(room.getStatus());
        room1.setCapacity(room.getCapacity());
        room1.setPrice(room.getPrice());
        return toDto(roomRepository.save(room1));
    }

    @Override
    public void deleteRoomById(long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room", id));
        roomRepository.delete(room);
    }

    private RoomDto toDto(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setRoomNumber(room.getRoomNumber());
        roomDto.setCapacity(room.getCapacity());
        roomDto.setPrice(room.getPrice());
        roomDto.setStatus(room.getStatus());
        return roomDto;
    }

    private Room toEntity(RoomDto roomDto) {
        Room room = new Room();
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setCapacity(roomDto.getCapacity());
        room.setPrice(roomDto.getPrice());
        room.setStatus(roomDto.getStatus());
        return room;
    }
}
