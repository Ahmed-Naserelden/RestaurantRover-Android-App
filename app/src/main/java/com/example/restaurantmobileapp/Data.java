package com.example.restaurantmobileapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

    private Map<String, List<Product>> productData;
    public Data(){
        productData = new HashMap<>();
    }

    public void addProduct(String  name,Product product){
        this.productData.get(name).add(product);
    }
    public void addProductList(String name, List products){
        this.productData.put(name, products);
    }
    public Map<String, List<Product>> getProductData() {
        return productData;
    }

    public void setProductData(Map<String, List<Product>> productData) {
        this.productData = productData;
    }
}
