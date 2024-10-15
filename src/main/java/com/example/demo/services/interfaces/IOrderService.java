package com.example.demo.services.interfaces;

import com.example.demo.dtos.OrderDto;
import com.example.demo.vms.OrderVM;

import java.util.List;

public interface IOrderService {

    OrderVM makeOrder(Long roomId);

    List<OrderVM> getOrdersByClientName(String username);

    List<OrderVM> getAllOrders();
    List<OrderVM> getPendingOrders();
    List<OrderVM> getAcceptedOrders();
    List<OrderVM> getRejectedOrders();

    OrderVM acceptOrder(Long id);
    OrderVM rejectOrder(Long id);

    void deleteOrder(Long id);
}
