package com.example.restaurantmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.ProtectionDomain;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Product p = new Product();

        FirebaseDatabase fir = FirebaseDatabase.getInstance();
        DatabaseReference ref = fir.getReference();

        ref.push().setValue(p);





    }
}