package com.example.restaurantmobileapp;

public class User {

    private String name, email;
    private Cart cart;
    private FavouriteProducts favouriteProducts;

    public User(){}
    public User(String name, String email, Cart cart, FavouriteProducts favouriteProducts) {
        this.name = name;
        this.email = email;
        this.cart = cart;
        this.favouriteProducts = favouriteProducts;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Cart getCart() {
        return cart;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public FavouriteProducts getFavouriteProducts() {
        return favouriteProducts;
    }
    public void setFavouriteProducts(FavouriteProducts favouriteProducts) {
        this.favouriteProducts = favouriteProducts;
    }
}
