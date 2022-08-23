package com.example.restaurantmobileapp;
import java.util.List;
import java.util.ArrayList;
public class FavouriteProducts {
    // favourite
    private List<Product> favouriteProduct;
    public FavouriteProducts (){
        favouriteProduct = (List<Product>) new ArrayList<Product>();
    }
    public void setFavouriteProduct(List<Product> favouriteProduct) {
        this.favouriteProduct = favouriteProduct;
    }
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
