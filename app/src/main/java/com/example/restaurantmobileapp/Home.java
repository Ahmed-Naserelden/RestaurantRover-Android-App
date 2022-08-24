package com.example.restaurantmobileapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
    ImageView imageView; DBModule db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = new DBModule();
        db.displayPicture(Product.path, "suba", imageView, this);
        String key = db.getmAuth().getUid();
        db.getDbRef().child("Users/"+key).addValueEventListener(new ValueEventListener() {
            User user;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
                Product p1 = new Product("fish", "sea food", "salt", 18.9f);
                db.addFavoriteProduct(p1, user, Home.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this, "Canceled  fngjbfjb", Toast.LENGTH_SHORT).show();
            }
        });
    }
}