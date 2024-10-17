package com.example.demo.vms;

import com.example.demo.models.enums.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomVM
{
    private Long id;
    private String title;
    private Integer price;
    private Integer capacity;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private RoomStatus status;
}
