package com.example.restaurantmobileapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class AdminPage extends AppCompatActivity implements View.OnClickListener {
    DBModule db;
    FirebaseStorage storage;
    StorageReference storageReference;
    EditText Name, Detail, Type, Price;
    String name, detail, type, price;
    ImageView imageView;
    Uri imageUri;
    Button btn;
    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        work();
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button4:
                name = Name.getText().toString();
                detail = Detail.getText().toString();
                type = Type.getText().toString();
                price = Price.getText().toString();
                if(name != "product name" && detail != "" && type != "product type" && price != "" && imageUri != null) {
                    product = new Product(name, type, detail, Float.parseFloat(price));
                    // Toast.makeText(this, name + " " + type + " " + price + " " + detail, Toast.LENGTH_SHORT).show();
                    db.addProduct(product, this);
                    db.uploadPicture("product/",product.getName(), imageUri, this);
                }else{
                    Toast.makeText(this, "some erorr in data", Toast.LENGTH_SHORT).show();
                    // Toast.makeText(this, name + " " + type + " " + price + " " + detail, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void work(){
        db = new DBModule();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        imageView = findViewById(R.id.imageView3);
        Name = findViewById(R.id.editTextTextPersonName2);
        Detail = findViewById(R.id.textInputEditText);
        Type = findViewById(R.id.editTextTextPersonName3);
        Price = findViewById(R.id.editTextNumberSigned);
        findViewById(R.id.button4).setOnClickListener(this);

        imageView.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 1);
                }
            }
        );
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

























// -------------------- warning region -------------------

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}