package com.example.demo.services.interfaces;

import com.example.demo.dtos.OrderDto;

import java.util.List;

public interface IOrderService {

    OrderDto makeOrder(OrderDto orderDto);

    List<OrderDto> getOrdersByClientName(String username);

    List<OrderDto> getAllOrders();
    List<OrderDto> getPendingOrders();
    List<OrderDto> getAcceptedOrders();
    List<OrderDto> getRejectedOrders();

    OrderDto acceptOrder(Long id);
    OrderDto rejectOrder(Long id);

    void deleteOrder(Long id);
}
