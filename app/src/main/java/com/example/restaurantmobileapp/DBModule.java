package com.example.restaurantmobileapp;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBModule {

    private DatabaseReference dbRef;
    public DBModule (){
        dbRef = FirebaseDatabase.getInstance().getReference("Restaurant");
    }

    // this product will be send to firebase
    public void addProduct(@NonNull Product product , Context context){
        dbRef.child("Products").child(product.getId()).setValue(product).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Success add product", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failure  add product", Toast.LENGTH_SHORT).show();

            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(context, "Canceled  add product", Toast.LENGTH_SHORT).show();

            }
        });
    }

    // this user will be send to firebase
    public void addUser(@NonNull User user, Context context){
        dbRef.child("Users").child(user.getUserId()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Success add user", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failure add user", Toast.LENGTH_SHORT).show();

            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(context, "Canceled add user", Toast.LENGTH_SHORT).show();

            }
        });
    }

    // this order will be send to firebase
    public void addOrder(@NonNull Order order, @NonNull User user, Context context){
        user.addOrder(order);
        dbRef.child("Users").child(user.getPhone()).child("cart").setValue(user.getCart()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Success add order", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failure add order", Toast.LENGTH_SHORT).show();

            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(context, "Canceled add order", Toast.LENGTH_SHORT).show();

            }
        });
    }

    // this changes will be send to firebase
    public void changeStateOrder(@NonNull Order order, @NonNull User user, String status, Context context){
        user.getCart().getOrders().get(order.getOrderID()).setOrderStatus(status);
        dbRef.child("Users").child(user.getPhone()).child("cart").setValue(user.getCart()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Success change StateOrder", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failure changeS tateOrder", Toast.LENGTH_SHORT).show();

            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(context, "Canceled change StateOrder", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void addFavoriteProduct(@NonNull Product product, @NonNull User user, Context context){
        user.addFavouriteProduct(product);
        dbRef.child("Users").child(user.getPhone()).child("favouriteProducts").setValue(user.getFavouriteProducts()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Success to add FavoriteProduct", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failure to add FavoriteProduct", Toast.LENGTH_SHORT).show();

            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(context, "Canceled to add FavoriteProduct", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void RemoveFavoriteProduct(@NonNull Product product, @NonNull User user, Context context){
        user.removeFavouriteProduct(product);
        dbRef.child("Users").child(user.getPhone()).child("favouriteProducts").setValue(user.getFavouriteProducts()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Success to Remove FavoriteProduct", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failure to Remove FavoriteProduct", Toast.LENGTH_SHORT).show();
            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(context, "Canceled to Remove FavoriteProduct", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
