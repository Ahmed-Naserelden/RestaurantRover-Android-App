package com.example.restaurantmobileapp;
import java.util.List;
import java.util.ArrayList;
public class FavouriteProducts {
    // favourite
    private List<Product> favouriteProduct = (List<Product>) new ArrayList<Product>();

    public List<Product> getFavouriteProducts() {
        return favouriteProduct;
    }

    public void addFavouriteProduct(Product product){
        favouriteProduct.add(product);
    }

    public void removeFavouriteProduct(Product product){
        favouriteProduct.add(product);
    }
}
