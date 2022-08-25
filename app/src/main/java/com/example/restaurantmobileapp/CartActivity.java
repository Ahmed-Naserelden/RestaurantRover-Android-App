package com.example.restaurantmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    DBModule db;
    User user;
    Order order;
    OrderBasedAdabter orderBasedAdabter;
    Map<String, Integer> productArrayList;
    TextView TotalPrice;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        listView = findViewById(R.id.list_viewCartItem);
        TotalPrice = findViewById(R.id.priceval);
        db = new DBModule();
        db.getDbRef().child("Users/"+db.mAuth.getUid()+"/order").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Order order = snapshot.getValue(Order.class);
                Map<String, Integer> products = order.getProducts();

                Double totalPrice = order.getTotalPrice();
                TotalPrice.setText(String.format("%.2f $", totalPrice));

                OrderBasedAdabter orderBasedAdabter = new OrderBasedAdabter(CartActivity.this,products);
                listView.setAdapter(orderBasedAdabter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CartActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}