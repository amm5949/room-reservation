package com.example.demo.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private String username;
    private String roomNumber;
    private LocalDateTime createdAt;
}
