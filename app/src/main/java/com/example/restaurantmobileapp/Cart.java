package com.example.restaurantmobileapp;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart {
    private List<Order> orders;
    public Cart(){
        orders = (List<Order>) new ArrayList<Order>();
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order){
        orders.add(order);
    }
    public void canOrder(@NonNull Order order){
        order.setOrderStatus("Cancled");
    }

    public void deleteOrder(@NonNull Order order){
        // deleting
    }


}
