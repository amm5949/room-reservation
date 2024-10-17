package com.example.demo.services;

import com.example.demo.exception.CustomBadRequestException;
import com.example.demo.exception.CustomNotFoundException;
import com.example.demo.models.Client;
import com.example.demo.models.Order;
import com.example.demo.models.Room;
import com.example.demo.models.UserPrincipal;
import com.example.demo.models.enums.OrderStatus;
import com.example.demo.models.enums.RoomStatus;
import com.example.demo.repositories.IClientRepository;
import com.example.demo.repositories.IOrderRepository;
import com.example.demo.repositories.IRoomRepository;
import com.example.demo.services.interfaces.IOrderService;
import com.example.demo.vms.ClientVM;
import com.example.demo.vms.OrderVM;
import com.example.demo.vms.RoomVM;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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

    @Transactional
    @Override
    public OrderVM makeOrder(Long roomId) {
        Order order = new Order();
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Client client = clientRepository.findByUsername(user.getUsername()).orElseThrow(()-> new CustomNotFoundException("Client Not Found"));
        Room room = roomRepository.findById(roomId).orElseThrow(()-> new CustomNotFoundException("Room Not Found"));
        if(room.getStatus() != RoomStatus.Available){
            throw new CustomBadRequestException("The Room you are trying to order is not Available");
        }
        order.setClient(client);
        order.setRoom(room);
        room.setStatus(RoomStatus.Reserved);
        order.setStatus(OrderStatus.Pending);
        return toVM(orderRepository.save(order));
    }

    @Override
    public List<OrderVM> getOrdersByContext() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderRepository.findAll().stream().filter(x-> x.getClient().getUsername().equals(userPrincipal.getUsername())).map(x->toVM(x)).collect(Collectors.toList());
        //username ignoreCase or default
    }

    @Override
    public List<OrderVM> getAllOrders() {
        return orderRepository.findAll().stream().map(x->toVM(x)).collect(Collectors.toList());
    }

    @Override
    public List<OrderVM> getPendingOrders() {
        return orderRepository.findAll().stream().filter(x-> x.getStatus() == OrderStatus.Pending).map(x->toVM(x)).collect(Collectors.toList());
    }

    @Override
    public List<OrderVM> getAcceptedOrders() {
        return orderRepository.findAll().stream().filter(x-> x.getStatus() == OrderStatus.Accepted).map(x->toVM(x)).collect(Collectors.toList());
    }

    @Override
    public List<OrderVM> getRejectedOrders() {
        return orderRepository.findAll().stream().filter(x-> x.getStatus() == OrderStatus.Rejected).map(x->toVM(x)).collect(Collectors.toList());
    }

    @Override
    public OrderVM acceptOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new CustomNotFoundException("Order not found"));
        order.setStatus(OrderStatus.Accepted);
        orderRepository.save(order);
        return toVM(order);
    }

    @Transactional
    @Override
    public OrderVM rejectOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new CustomNotFoundException("Order not found"));
        order.setStatus(OrderStatus.Rejected);
        orderRepository.save(order);
        Room room = roomRepository.findById(order.getRoom().getId()).orElseThrow(()->new CustomNotFoundException("Room not found"));
        room.setStatus(RoomStatus.Available);
        return toVM(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new CustomNotFoundException("Order not found"));
        order.setStatus(OrderStatus.Canceled);
        orderRepository.save(order);
    }

    public boolean checkOrderStats(Long id) {
        return orderRepository.findById(id)
                .map(order -> order.getStatus() == OrderStatus.Accepted)
                .orElse(false);
    }

    public OrderVM toVM(Order order){
        OrderVM orderVM = new OrderVM();
        orderVM.setOrderId(order.getId());
        orderVM.setClientUsername(order.getClient().getUsername());
        orderVM.setRoomTitle(order.getRoom().getTitle());
        orderVM.setOrderStatus(order.getStatus().name());
        return orderVM;
    }

    public ClientVM toClientVM(Client client){
        ClientVM clientVM = new ClientVM();
        clientVM.setUsername(client.getUsername());
        clientVM.setClientId(client.getId());
        return clientVM;
    }

    public RoomVM toRoomVM(Room room){
        RoomVM roomVM = new RoomVM();
        roomVM.setTitle(room.getTitle());
        roomVM.setId(room.getId());
        roomVM.setStatus(room.getStatus());
        roomVM.setImageUrl(room.getImageUrl());
        roomVM.setCapacity(room.getCapacity());
        roomVM.setPrice(room.getPrice());
        return roomVM;
    }
}