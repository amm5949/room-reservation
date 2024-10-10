package com.example.demo.services;

import com.example.demo.dtos.OrderDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.Client;
import com.example.demo.models.Manager;
import com.example.demo.models.Order;
import com.example.demo.models.Room;
import com.example.demo.models.enums.OrderStatus;
import com.example.demo.repositories.IClientRepository;
import com.example.demo.repositories.IManagerRepository;
import com.example.demo.repositories.IOrderRepository;
import com.example.demo.repositories.IRoomRepository;
import com.example.demo.services.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerService implements IManagerService {


    @Autowired
    IManagerRepository managerRepository;

    @Override
    public Manager registerManager(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public void deleteManager(Long managerId) {
        managerRepository.findById(managerId).orElseThrow(() -> new NotFoundException("Manager not found"));
        managerRepository.deleteById(managerId);
    }

    // TODO: change update method for Manager
    @Override
    public Manager updateManager(Manager newManager, Long managerId) {
        Manager manager = managerRepository.findById(managerId).orElseThrow(() -> new NotFoundException("Manager not found"));
        manager.setUserName(newManager.getUserName());
        manager.setPassword(newManager.getPassword());
        return managerRepository.save(manager);
    }
}
