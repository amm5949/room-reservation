package com.example.demo.repositories;

import com.example.demo.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IManagerRepository extends JpaRepository<Manager, Long> {

}
