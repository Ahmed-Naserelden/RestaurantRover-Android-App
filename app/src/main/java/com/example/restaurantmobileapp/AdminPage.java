package com.example.restaurantmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

public class AdminPage extends AppCompatActivity implements View.OnClickListener {

    EditText Name, Detail, Type, Price;
    String name, detail, type, price;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        imageView = findViewById(R.id.imageView3);
        Name = findViewById(R.id.editTextTextPersonName2);
        Detail = findViewById(R.id.textInputEditText);
        Type = findViewById(R.id.editTextTextPersonName3);
        Price = findViewById(R.id.editTextNumberSigned);
        findViewById(R.id.button4).setOnClickListener(this);
        name = Name.getText().toString();
        detail = Detail.getText().toString();
        type = Type.getText().toString();
        price = Price.getText().toString();

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button4:
                Product product = new Product();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}