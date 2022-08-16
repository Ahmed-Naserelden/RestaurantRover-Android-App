package com.example.restaurantmobileapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order{
    private HashMap<Product, Integer> products;
    private String orderStatus;
    private double totalPrice;
    public Order(){
        totalPrice = 0.0f;
        orderStatus = "Waiting";
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }
    public void setProducts(HashMap<Product, Integer> products) {
        this.products = products;
    }
    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }




// **********************************************************************************
    public void addProduct(Product product){
//        orders.merge(product, 1, Integer::sum);
        int count = products.containsKey(product) ? products.get(product) : 0;
        products.put(product, count + 1);
        totalPrice += product.getPrice();
    }
    public void decrementProduct(Product product){
        int count = products.containsKey(product) ? products.get(product) : 0;
        if(count > 0) {
//          orders.merge(product, -1, Integer::sum);
            products.put(product, count -1);
            totalPrice -= product.getPrice();
        }
    }
}
