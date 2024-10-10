package com.example.demo.repositories;

import com.example.demo.models.Manager;
import jdk.jpackage.internal.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IManagerRepository extends JpaRepository<Manager, Long> {

}
