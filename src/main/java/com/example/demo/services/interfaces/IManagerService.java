package com.example.demo.services.interfaces;

import com.example.demo.dtos.OrderDto;
import com.example.demo.models.Manager;
import com.example.demo.models.Order;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IManagerService {

    Manager registerManager(Manager manager);

    List<Manager> getAllManagers();

    void deleteManager(Long managerId);

    Manager updateManager(Manager manager,Long managerId);
}
