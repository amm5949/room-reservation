package com.example.demo.db;

import com.example.demo.models.*;

import java.util.ArrayList;

public class Db
{
    public static ArrayList<Person> people;
    public static ArrayList<Room> rooms;
    public static ArrayList<Order> orders;
    public static Person loggedInUser = new Client("test","1234",1000);

    private static void seedPeople(){
        people.add(new Client("client1","1234",1000));
        people.add(new Client("client2","1234",1000));
        people.add(new Manager("manager","1234"));
        people.add(new Admin("admin","1234"));
    }

    private static void seedRooms(){
        rooms.add(new Room(5,200,true));
        rooms.add(new Room(6,250,true));
        rooms.add(new Room(7,280,true));
        rooms.add(new Room(8,320,true));
    }

    private static void seedOrders(){
        orders.add(new Order((Client) people.get(0), rooms.get(0),false));
        orders.add(new Order((Client) people.get(1), rooms.get(1),true));
        orders.add(new Order((Client) people.get(2), rooms.get(2),false));
    }

    public static void seedDatabase(){
        seedPeople();
        seedRooms();
        seedOrders();
    }
}
