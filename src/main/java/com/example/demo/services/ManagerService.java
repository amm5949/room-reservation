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
        Manager manager = managerRepository.findById(managerId).
                orElseThrow(() -> new CustomNotFoundException(("Manager not found with Id : " + managerId)));
        managerRepository.deleteById(manager.getId());
    }

    // TODO: change update method for Manager
    @Override
    public Manager updateManager(Manager newManager, Long managerId) {
        return managerRepository.findById(managerId)
                .map(manager1 -> {
                    manager1.setPassword(newManager.getPassword());
                    manager1.setUsername(newManager.getUsername());
                    return managerRepository.save(manager1);
                }).orElseThrow(() -> new CustomNotFoundException(("Manager with Id " + managerId + " not found!")));
    }
}
