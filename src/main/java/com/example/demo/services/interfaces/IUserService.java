package com.example.demo.services.interfaces;


import com.example.demo.dtos.AuthDto;

public interface IUserService
{
    String register(AuthDto authDto);
    String login(AuthDto authDto);
}
