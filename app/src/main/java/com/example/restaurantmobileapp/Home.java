package com.example.restaurantmobileapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
    ImageView imageView; DBModule db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = new DBModule();
        imageView = findViewById(R.id.imageView4);
        db.displayPicture(Product.path, "suba", imageView, this);
    }
}