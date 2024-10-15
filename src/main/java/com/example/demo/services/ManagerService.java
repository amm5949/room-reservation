package com.example.demo.services;

import com.example.demo.exception.CustomNotFoundException;
import com.example.demo.models.Manager;
import com.example.demo.repositories.IManagerRepository;
import com.example.demo.services.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<Manager> managers = managerRepository.findAll();

        if (managers.isEmpty()) {
            throw new NotFoundException("Manager list is empty!");
        }
        return managers;
    }

    @Override
    public void deleteManager(Long managerId) {
<<<<<<< HEAD
        managerRepository.findById(managerId).orElseThrow(() -> new CustomNotFoundException("Manager not found"));
        managerRepository.deleteById(managerId);
=======
        Manager manager = managerRepository.findById(managerId).
                orElseThrow(() -> new NotFoundException("Manager not found with Id : " + managerId));
        managerRepository.deleteById(manager.getId());
>>>>>>> swagger
    }

    // TODO: change update method for Manager
    @Override
    public Manager updateManager(Manager newManager, Long managerId) {
<<<<<<< HEAD
        Manager manager = managerRepository.findById(managerId).orElseThrow(() -> new CustomNotFoundException("Manager not found"));
        manager.setUsername(newManager.getUsername());
        manager.setPassword(newManager.getPassword());
        return managerRepository.save(manager);
=======
        return managerRepository.findById(managerId)
                .map(manager -> {
                    manager.setPassword(newManager.getPassword());
                    manager.setUserName(newManager.getUserName());
                    return managerRepository.save(manager);
                }).orElseThrow(() -> new NotFoundException("Manager with Id " + managerId + " not found!"));
>>>>>>> swagger
    }
}
