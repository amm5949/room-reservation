package com.example.demo;


import com.example.demo.models.*;
import com.example.demo.models.enums.OrderStatus;
import com.example.demo.models.enums.RoomStatus;
import com.example.demo.repositories.IClientRepository;
import com.example.demo.repositories.IOrderRepository;
import com.example.demo.repositories.IRoomRepository;
import com.example.demo.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoomRepository roomRepository;
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        userRepository.save(new Admin("admin", passwordEncoder.encode("admin"), "admin@email.com"));
        userRepository.save(new Manager("manager", passwordEncoder.encode("manager"), "manager@email.com"));
        userRepository.save(new Client("client", passwordEncoder.encode("client"), "client@email.com"));
        roomRepository.save(new Room(1L, "Luxury", 1500, 15, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSp_ZzeqvKBrERhI0dO_t2shraAsWQHZPcUYg&s", RoomStatus.Reserved, null));
        roomRepository.save(new Room(2L, "Poor", 150, 4, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSam0svG7i7ZrujnhZVeKQfE-5tyMVbTOrcwA&s", RoomStatus.Available, null));
        roomRepository.save(new Room(3L, "Medium", 500, 6, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQfhd5KOQpbClOaOpCUbkj-cmSboIPpGOLvnQ&s", RoomStatus.Maintenance, null));
        roomRepository.save(new Room(4L, "Sky", 2500, 20, "https://hiddenarchitecture.net/wp-content/uploads/2015/04/skyhouse_016-iwan-baan.jpg", RoomStatus.Available, null));

        orderRepository.save(new Order(1L, roomRepository.findById(1L).get(), clientRepository.findById(3L).get(), OrderStatus.Pending));
        orderRepository.save(new Order(2L, roomRepository.findById(2L).get(), clientRepository.findById(3L).get(), OrderStatus.Rejected));
        orderRepository.save(new Order(3L, roomRepository.findById(3L).get(), clientRepository.findById(3L).get(), OrderStatus.Accepted));
        orderRepository.save(new Order(4L, roomRepository.findById(4L).get(), clientRepository.findById(3L).get(), OrderStatus.Canceled));
    }


}
