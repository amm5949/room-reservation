package com.example.demo.services.interfaces;


import com.example.demo.dtos.AuthDto;
import com.example.demo.vms.AuthVM;

public interface IUserService
{
    AuthVM register(AuthDto authDto);
    AuthVM login(AuthDto authDto);
}
