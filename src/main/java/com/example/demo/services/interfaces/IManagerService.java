package com.example.demo.services.interfaces;

import com.example.demo.dtos.OrderDto;
import com.example.demo.models.Order;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IManagerService {


    List<OrderDto> getOrderLists();

    OrderDto acceptOrder(long orderId);

    OrderDto declienOrder(long orderId);
}
