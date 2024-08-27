package com.example.demo.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Order
{
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static int idInc;
    private int orderId;
    private Client client;
    private Room room;
    private boolean isRegistered;

    public Order(Client client, Room room, boolean isRegistered) {
        this.client = client;
        this.room = room;
        this.isRegistered = isRegistered;
        setOrderId();
    }

    private void setOrderId(){
        this.orderId = idInc++;
    }
}
