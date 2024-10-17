package com.example.demo.dtos;

import com.example.demo.models.enums.RoomStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@AllArgsConstructor
@Data
public class RoomDto
{
    private String title;
    private Integer price;
    private Integer capacity;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private RoomStatus status;
}
