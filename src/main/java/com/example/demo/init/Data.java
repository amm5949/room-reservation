package com.example.demo.init;

import com.example.demo.models.*;
import com.example.demo.models.enums.OrderStatus;
import com.example.demo.models.enums.RoomStatus;
import com.example.demo.repositories.IClientRepository;
import com.example.demo.repositories.IOrderRepository;
import com.example.demo.repositories.IRoomRepository;
import com.example.demo.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Data {
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

    public void SeedDatabase(){
        userRepository.save(new Admin("admin", passwordEncoder.encode("admin"), "admin@email.com"));
        userRepository.save(new Manager("manager", passwordEncoder.encode("manager"), "manager@email.com"));
        userRepository.save(new Client("client", passwordEncoder.encode("client"), "client@email.com"));

        roomRepository.save(new Room(1L, "Luxury Suite", 1500, 15, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSp_ZzeqvKBrERhI0dO_t2shraAsWQHZPcUYg&s", RoomStatus.Available, null));
        roomRepository.save(new Room(2L, "Executive Room", 250, 2, "https://cdn.marriottnetwork.com/uploads/sites/23/2022/10/Executive-Suite-Living-and-Dining-Rooms-5091-788x650.jpg", RoomStatus.Available, null));
        roomRepository.save(new Room(3L, "Deluxe Room", 200, 2, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfSBJutkDAwZMqXHdZMNi6hDOZCFIOYVccQQ&s", RoomStatus.Reserved, null));
        roomRepository.save(new Room(4L, "Family Room", 150, 4, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZ3t8XLobtw46daUdj9YrRyL1ajfgSWWZ8uA&s", RoomStatus.Available, null));
        roomRepository.save(new Room(5L, "Single Room", 100, 1, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvyTxDZ71BEqtpmSsZpBiK_XsAWXaxX3FhFw&s",RoomStatus.Available, null));
        roomRepository.save(new Room(6L, "Studio Room", 180, 2, "https://design-tips.floorplanner.com/wp-content/uploads/2021/02/5692effb27a05d4fbb5609feb010764e6eb3f513.jpg", RoomStatus.Available, null));
        roomRepository.save(new Room(7L, "Penthouse", 500, 8, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTS7UeuUIdkL_6vIsrl2uWoOWn4ExybDboqdg&s", RoomStatus.Available, null));




        orderRepository.save(new Order(1L, roomRepository.findById(1L).get(), clientRepository.findById(3L).get(), OrderStatus.Pending));
        orderRepository.save(new Order(2L, roomRepository.findById(2L).get(), clientRepository.findById(3L).get(), OrderStatus.Rejected));
        orderRepository.save(new Order(3L, roomRepository.findById(3L).get(), clientRepository.findById(3L).get(), OrderStatus.Accepted));
        orderRepository.save(new Order(4L, roomRepository.findById(4L).get(), clientRepository.findById(3L).get(), OrderStatus.Canceled));
    }
}
