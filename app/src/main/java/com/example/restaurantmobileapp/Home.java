package com.example.restaurantmobileapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.restaurantmobileapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    ImageView imageView; DBModule db;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = new DBModule();
        listView = findViewById(R.id.list_view);

        ArrayList<Product> productArrayList = new ArrayList<>();

        db.getDbRef().child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot i : snapshot.getChildren()){
                    for(DataSnapshot j : i.getChildren()){
                        Product product = j.getValue(Product.class);
                        productArrayList.add(product);
                    }
                }
                customBaseAdabter customAdabter = new customBaseAdabter(getApplicationContext(), productArrayList);
                listView.setAdapter(customAdabter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Toast.makeText(Home.this, productArrayList.get(i).getName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                );
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void love(View view){
        Toast.makeText(this, "AAAAAAAA", Toast.LENGTH_SHORT).show();
    }

}
/*
ArrayList<Product> productList = new ArrayList<>();
        db.getDbRef().child("Product").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot i : snapshot.getChildren()){
                    for (DataSnapshot j : i.getChildren()){
                        Product product = j.getValue(Product.class);
                        productList.add(product);
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this, "ERORR", Toast.LENGTH_SHORT).show();
            }
        });
 */