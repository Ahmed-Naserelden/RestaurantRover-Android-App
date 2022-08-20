package com.example.restaurantmobileapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.security.ProtectionDomain;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Uri path;
    final static int CHOOSE_IMAGE = 1;
    EditText editText;
    ImageView imageView;
    Intent outIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        goToLogin();
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.loginBtn).setOnClickListener(this);

    }

    public void test(View view){
        editText = (EditText) findViewById(R.id.editTextTextPersonName);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int req, int res, @Nullable Intent intent) {
        super.onActivityResult(req, res, intent);
        if(req == CHOOSE_IMAGE && req == RESULT_OK && intent != null && intent.getData() != null){
            path = intent.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private  void showImages(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selet Image"), CHOOSE_IMAGE);
    }

    public void save(View view){
//        FirebaseDatabase fir = FirebaseDatabase.getInstance();
//        DatabaseReference ref = fir.getReference();
//
 //       DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Restaurant");

//        DocumentReference mdoc = FirebaseFirestore.getInstance().document("Restaurant/Users");
        Product p1 = new Product("fish", "sea food", "salt", 18.9f);
        Product p2 = new Product("milk", "drinks", "safe", 2.9f);
        Product p3 = new Product("rise", "food", "spice", 5.9f);
        Product p4 = new Product("water", "drinks", "good", 1.9f);

        Order order1 = new Order();
        order1.addProduct(p1);order1.addProduct(p2);order1.addProduct(p3);order1.addProduct(p4);

        Order order2 = new Order();
        order2.addProduct(p1);order2.addProduct(p2);order2.addProduct(p3);order2.addProduct(p4);
        order2.addProduct(p2);

        Cart cart = new Cart();
        cart.addOrder(order1);
        cart.addOrder(order2);

        FavouriteProducts favouriteProducts = new FavouriteProducts();
        favouriteProducts.addFavouriteProduct(p1);
        favouriteProducts.addFavouriteProduct(p2);

        String NAME = "Naser eldin Mohamed Amer";
        String EMAIL = "Naser@gmail.com";
        String phone = "01003129806";

        User user = new User(NAME, EMAIL, phone, cart, favouriteProducts);
        user.setPassword("veryStrong");
        DBModule db = new DBModule();
        db.RemoveFavoriteProduct(p3, user, this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                startActivity(new Intent(this, SignUp.class));
                break;
            case R.id.loginBtn:
                startActivity(new Intent(this, SignIn.class));
                break;
        }
    }

    private void goToLogin(){
        outIntent = new Intent(this, SignIn.class);
        //outIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(outIntent);

    }
}