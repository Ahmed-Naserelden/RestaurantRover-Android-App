package com.example.restaurantmobileapp;

public class User {
    private String name, email;
    private Cart cart; //
    private FavouriteProducts favouriteProducts;
    private String phone, password;
    private Order order;
    public User(){
        this.cart = new Cart();
        this.favouriteProducts =  new FavouriteProducts();
        order = new Order();
    }
    public User(String userId, String email){
        this.email = email;
        this.name = "";
        this.cart = new Cart();
        order = new Order();
        this.favouriteProducts =  new FavouriteProducts();
    }


    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.cart = new Cart();
        this.favouriteProducts =  new FavouriteProducts();
        order = new Order();
    }
    public User(String name, String email, String phone, Cart cart, FavouriteProducts favouriteProducts) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cart = cart;
        this.favouriteProducts = favouriteProducts;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
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
        return this.favouriteProducts;
    }
    public void setFavouriteProducts(FavouriteProducts favouriteProducts) {
        this.favouriteProducts = favouriteProducts;
    }
    public void addOrder(Order order){
        this.cart.addOrder(order);
    }
    public void canOrder(Order order){this.cart.canOrder(order);}
    public void addFavouriteProduct(Product product){
        this.favouriteProducts.addFavouriteProduct(product);
    }
    public void removeFavouriteProduct(Product product){
        this.favouriteProducts.removeFavouriteProduct(product);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void addProductToOrder(Product product){
        this.order.addProduct(product);
    }
}
