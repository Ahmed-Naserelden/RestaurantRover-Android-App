package com.example.restaurantmobileapp;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order{

    private HashMap<String, Integer> products;
    private String orderStatus;
    private double totalPrice;
    public Order(){
        products = new HashMap<>();
        totalPrice = 0.0f;
        orderStatus = "Waiting";
    }

//    @Exclude
    public HashMap<String, Integer> getProducts() {
        return products;
    }
    public void setProducts(HashMap<String, Integer> products) {
        this.products = products;
    }
    public String getOrderStatus() {
        return this.orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public double getTotalPrice() {
        return this.totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }




// **********************************************************************************

//    @Exclude
    public void addProduct(Product product){
//        orders.merge(product, 1, Integer::sum);
        int count = products.containsKey(product.getId()) ? products.get(product.getId()) : 0;
        products.put(product.getId(), count + 1);
        totalPrice += product.getPrice();
    }
//    @Exclude
    public void decrementProduct(Product product){
        int count = products.containsKey(product.getId()) ? products.get(product.getId()) : 0;
        if(count > 0) {
//          orders.merge(product, -1, Integer::sum);
            products.put(product.getId(), count -1);
            totalPrice -= product.getPrice();
        }
    }
}
