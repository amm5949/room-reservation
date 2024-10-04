package com.example.demo.dtos;

import com.example.demo.models.enums.RoomStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
public class RoomDto
{
    private String roomNumber;
    private Integer price;
    private Integer capacity;
    @Enumerated(EnumType.STRING)
    private RoomStatus status;
}
