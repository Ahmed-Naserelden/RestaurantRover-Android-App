package com.example.restaurantmobileapp;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart {
    private List<Order> orders;
//    public int size;
    public Cart(){
        orders = (List<Order>) new ArrayList<Order>();
//        size = 0;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(@NonNull Order order){
        order.setOrderID(orders.size());
//        size++;
        this.orders.add(order);
    }
    public void canOrder(@NonNull Order order){
        order.setOrderStatus("Cancled");
    }


    public void changeOrderStatus(int id){
        this.orders.get(id).setOrderStatus("");
    }
    public void deleteOrder(@NonNull Order order){
        // deleting
    }


}
