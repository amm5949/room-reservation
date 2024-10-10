package com.example.demo.services;

import com.example.demo.dtos.OrderDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.Client;
import com.example.demo.models.Order;
import com.example.demo.models.Room;
import com.example.demo.models.enums.OrderStatus;
import com.example.demo.repositories.IClientRepository;
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
    IOrderRepository orderRepository;

    @Autowired
    IClientRepository clientRepository;
    @Autowired
    IRoomRepository roomRepository;

    @Override
    public List<OrderDto> getOrderLists() {
        return orderRepository.findAll().stream().map(x -> toDto(x)).collect(Collectors.toList());
    }

    @Override
    public OrderDto acceptOrder(long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found"));
        order.setStatus(OrderStatus.Accepted);
        orderRepository.save(order);
        return toDto(order);
    }

    @Override
    public OrderDto declienOrder(long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found"));
        order.setStatus(OrderStatus.Rejected);
        orderRepository.save(order);
        return toDto(order);
    }

    public OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setRoomNumber(order.getRoom().getRoomNumber());
        orderDto.setUsername(order.getClient().getUserName());
        orderDto.setCreatedAt(order.getCreatedAt());
        return orderDto;
    }
}
