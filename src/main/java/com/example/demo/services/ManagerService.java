package com.example.demo.services;

import com.example.demo.exception.CustomNotFoundException;
import com.example.demo.models.Manager;
import com.example.demo.repositories.IManagerRepository;
import com.example.demo.services.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new CustomNotFoundException("Manager list is empty!");
        }
        return managers;
    }

    @Override
    public void deleteManager(Long managerId) {

        managerRepository.findById(managerId).orElseThrow(() -> new CustomNotFoundException("Manager not found"));
        managerRepository.deleteById(managerId);

        Manager manager = managerRepository.findById(managerId).
                orElseThrow(() -> new CustomNotFoundException("Manager not found with Id : " + managerId));
        managerRepository.deleteById(manager.getId());

    }

    // TODO: change update method for Manager
    @Override
    public Manager updateManager(Manager newManager, Long managerId) {

        Manager manager = managerRepository.findById(managerId).orElseThrow(() -> new CustomNotFoundException("Manager not found"));
        manager.setUsername(newManager.getUsername());
        manager.setPassword(newManager.getPassword());
        return managerRepository.save(manager);
    }
}