package com.example.restaurantmobileapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.common.base.MoreObjects;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    DBModule db;
    ImageView imageView;
    Intent outIntent;
    Uri imageUri;
    FirebaseStorage storage;
    StorageReference storageReference;
    DatabaseReference dbRef;
    Data data;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();




//        dbRef.child("Products").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot i : snapshot.getChildren()) {
//                    List<Product> li = (List<Product>) new ArrayList<Product>();
//                    // TODO: handle the post
//                    for (DataSnapshot j : i.getChildren()) {
//                        // TODO: handle the post
//                        Product product = j.getValue(Product.class);
//                        li.add(product);
//                    }
//                    if(li.isEmpty()){
//                        Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(MainActivity.this, "Not Empty", Toast.LENGTH_SHORT).show();
//                    }
//                    // Toast.makeText(MainActivity.this, i.getKey(), Toast.LENGTH_SHORT).show();
//                    data.addProductList(i.getKey(), li);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity.this, "Faliure", Toast.LENGTH_SHORT).show();
//            }
//
//        });
        //mCheckInforInServer("Products");
//        dbRef.child("Products").get().addOnCompleteListener(
//                new OnCompleteListener<DataSnapshot>() {
//
//                    @Override
//                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//                        if (task.isSuccessful()) {
//                            List<Product> li = (List<Product>) new ArrayList<Product>();
//                            // Toast.makeText(MainActivity.this, task.getResult().getValue().toString(), Toast.LENGTH_SHORT).show();
//                            for (DataSnapshot i : task.getResult().getChildren()) {
//                                for (DataSnapshot j : i.getChildren()) {
//                                    Product product = j.getValue(Product.class);
//                                    li.add(product);
//
//                                }
//                                if (li.isEmpty()) {
//                                    Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(MainActivity.this, "Not Empty", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        } else {
//                            Toast.makeText(MainActivity.this, "faliur", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//        );
//        fun();
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                {
////                     your code once you get the data
//                    fun();
//                }
//
//            }
//        }, 2000);
//        fun();
    }

// *******************************IMAGES*********************************
    public void fun(){
        Toast.makeText(this, data.getProductData().toString(), Toast.LENGTH_SHORT).show();

    }
    private void newWork(){
        imageView = findViewById(R.id.imageView);
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
            db.uploadPicture("user/","ahmed",imageUri, this);
        }
    }
    private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image ...");
        pd.show();
        final String randomKey = UUID.randomUUID().toString();
        storageReference.child("images/"+ randomKey).putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Snackbar.make(findViewById(android.R.id.content), "image Uploaded", Snackbar.LENGTH_LONG).show();
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, "Upload Canceled", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, "Upload Failure", Toast.LENGTH_SHORT).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent = (100.0*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                pd.setMessage("Percentage : " + (int) progressPercent + "%");
                if((int) progressPercent == 100){
                    pd.dismiss();
                }
            }
        });
    }
//************************************************************************
//    @Override
//    protected void onActivityResult(int req, int res, @Nullable Intent intent) {
//        super.onActivityResult(req, res, intent);
//        if(req == CHOOSE_IMAGE && req == RESULT_OK && intent != null && intent.getData() != null){
//            path = intent.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
//                imageView.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
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
        db.addFavoriteProduct(p4, user, this);
       // db.RemoveFavoriteProduct(p3, user, this);

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

    private void mCheckInforInServer(String child) {
        new DBModule().mReadDataOnce(child, new OnGetDataListener() {
            @Override
            public void onStart() {
                //DO SOME THING WHEN START GET DATA HERE
            }

            @Override
            public void onSuccess(DataSnapshot data) {
                //DO SOME THING WHEN GET DATA SUCCESS HERE
                List<Product> li = (List<Product>) new ArrayList<Product>();
                // Toast.makeText(MainActivity.this, task.getResult().getValue().toString(), Toast.LENGTH_SHORT).show();
                for (DataSnapshot i : data.getChildren()) {
                    for (DataSnapshot j : i.getChildren()) {
                        Product product = j.getValue(Product.class);
                        li.add(product);
                    }
                    if (li.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Not Empty", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                //DO SOME THING WHEN GET DATA FAILED HERE
            }
        });

    }

    public void start(){
        db = new DBModule();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        dbRef = FirebaseDatabase.getInstance().getReference("Restaurant");
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.loginBtn).setOnClickListener(this);
    }


    public void yousef(){
        data = db.getProductData("Products",this);
        if(data.getProductData().isEmpty()){
            Toast.makeText(this, "Empty ..", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "not Empty ...", Toast.LENGTH_SHORT).show();
        }
    }
}