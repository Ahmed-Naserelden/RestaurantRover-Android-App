package com.example.restaurantmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
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
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        listView = findViewById(R.id.list_viewcart);

        db = new DBModule();
        db.getDbRef().child("Users/"+db.mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
                Toast.makeText(CartActivity.this, user.getName(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CartActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        db.getDbRef().child("Users/"+db.mAuth.getUid()+"/order").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                order = snapshot.getValue(Order.class);
                productArrayList= order.getProducts();
                Toast.makeText(CartActivity.this, String.format("%s",productArrayList.get("water")), Toast.LENGTH_SHORT).show();
                orderBasedAdabter = new OrderBasedAdabter(CartActivity.this,  productArrayList);
                listView.setAdapter(orderBasedAdabter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}