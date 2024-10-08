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
import com.example.demo.services.interfaces.IOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IClientRepository clientRepository;
    private final IRoomRepository roomRepository;

    public OrderService(IOrderRepository orderRepository, IClientRepository clientRepository, IRoomRepository roomRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public OrderDto makeOrder(OrderDto orderDto) {
        return toDto(orderRepository.save(toEntity(orderDto)));
    }

    @Override
    public List<OrderDto> getOrdersByClientName(String username) {
        return orderRepository.findAll().stream().filter(x-> x.getClient().getUserName().equals(username)).map(x->toDto(x)).collect(Collectors.toList());
        //username ignoreCase or default
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(x->toDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getPendingOrders() {
        return orderRepository.findAll().stream().filter(x-> x.getStatus() == OrderStatus.Pending).map(x->toDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAcceptedOrders() {
        return orderRepository.findAll().stream().filter(x-> x.getStatus() == OrderStatus.Accepted).map(x->toDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getRejectedOrders() {
        return orderRepository.findAll().stream().filter(x-> x.getStatus() == OrderStatus.Rejected).map(x->toDto(x)).collect(Collectors.toList());
    }

    @Override
    public OrderDto acceptOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new NotFoundException("Order not found"));
        order.setStatus(OrderStatus.Accepted);
        orderRepository.save(order);
        return toDto(order);
    }

    @Override
    public OrderDto rejectOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new NotFoundException("Order not found"));
        order.setStatus(OrderStatus.Rejected);
        orderRepository.save(order);
        return toDto(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new NotFoundException("Order not found"));
        order.setStatus(OrderStatus.Canceled);
        orderRepository.save(order);
    }

    public Order toEntity(OrderDto orderDto) {
        Order order = new Order();
        Client client = clientRepository.findByUsername(orderDto.getUsername()).orElseThrow(()-> new NotFoundException("Client Not Found"));
        Room room = roomRepository.findByRoomNumber(orderDto.getRoomNumber()).orElseThrow(()-> new NotFoundException("Room Not Found"));
        order.setClient(client);
        order.setRoom(room);
        order.setStatus(OrderStatus.Pending);
        return order;
    }

    public OrderDto toDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setRoomNumber(order.getRoom().getRoomNumber());
        orderDto.setUsername(order.getClient().getUserName());
        orderDto.setCreatedAt(order.getCreatedAt());
        return orderDto;
    }
}
