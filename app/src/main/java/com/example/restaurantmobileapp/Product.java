package com.example.restaurantmobileapp;

import java.util.HashMap;

public class Product {
    private String name, type, detail;
    private String Id;
    private float price;
    private String imgPath;
    Product(){
        name = "";
        type = "";
        detail = "";
        price = 0.0f;
        imgPath = "";
    }

    public Product(String name, String type, String detail, float price) {
        this.name = name;
        this.type = type;
        this.detail = detail;
        this.price = price;
        this.Id = name;
        imgPath = "";
    }

    public String getId() {
        return Id;
    }

//    public void setId(String id) {
//        Id = id;
//    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        this.Id = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getImgPath() {
        return imgPath;
    }
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
