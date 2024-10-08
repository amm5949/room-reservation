package com.example.demo.services;

import com.example.demo.dtos.OrderDto;
import com.example.demo.models.Order;
import com.example.demo.services.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService implements IManagerService {


    @Autowired
    OrderService orderService;

    @Override
    public List<OrderDto> getOrderLists() {
        return orderService.getAllOrders();
    }

    @Override
    public OrderDto acceptOrder(long orderId) {
        return orderService.acceptOrder(orderId);
    }

    @Override
    public OrderDto declienOrder(long orderId) {
        return orderService.rejectOrder(orderId);
    }
}
