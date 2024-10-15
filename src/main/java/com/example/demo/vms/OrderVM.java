package com.example.demo.vms;

import lombok.Data;

@Data
public class OrderVM
{
    private Long orderId;
    private String clientUsername;
    private String roomTitle;
    private String orderStatus;
}
