package com.example.demo.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Room
{
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static int idInc;
    private int roomId;
    private int roomNumber;
    private int capacity;
    private boolean isReserved;

    public Room(int roomNumber, int capacity, boolean isReserved) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.isReserved = isReserved;
        setRoomId();
    }

    private void setRoomId(){
        this.roomId = idInc++;
    }
}
