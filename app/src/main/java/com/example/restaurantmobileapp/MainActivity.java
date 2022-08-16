package com.example.restaurantmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.annotation.Documented;
import java.security.ProtectionDomain;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save();
    }
    public void save(){
        FirebaseDatabase fir = FirebaseDatabase.getInstance();
        DatabaseReference ref = fir.getReference("Restaurant");

//        DocumentReference mdoc = FirebaseFirestore.getInstance().document("Restaurant/Users");
        Product p = new Product();
        p.setName("rich");
        p.setDetail("slat");
        p.setType("food");
        p.setId("foo");
        p.setPrice(15.3f);
        Order order = new Order();
        order.addProduct(p);

        ref.child("Users").push().setValue(order).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();

                    }
                }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(MainActivity.this, "Canceled", Toast.LENGTH_SHORT).show();

            }
        });
    }
}