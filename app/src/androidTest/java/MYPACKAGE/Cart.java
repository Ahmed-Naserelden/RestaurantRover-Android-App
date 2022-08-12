package MYPACKAGE;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    HashMap<Product, Integer> orders;
    private double totalPrice;
    public Cart(){
        totalPrice = 0.0f;
    }
    public HashMap<Product, Integer> getOrders() {
        return orders;
    }
    public void setOrders(HashMap<Product, Integer> orders) {
        this.orders = orders;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void addProduct(Product product){
        orders.merge(product, 1, Integer::sum);
        totalPrice += product.getPrice();
    }
    public void decrementProduct(Product product){
        int count = orders.containsKey(product) ? orders.get(product) : 0;
        if(count > 0) {
            orders.merge(product, -1, Integer::sum);
            totalPrice -= product.getPrice();
        }
    }
}
