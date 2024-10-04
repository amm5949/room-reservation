package com.example.demo.repository;

import com.example.demo.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRespository extends JpaRepository<Client, Long> {
}
