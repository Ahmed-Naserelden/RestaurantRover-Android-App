package com.example.restaurantmobileapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final static int CHOOSE_IMAGE = 1;
    EditText editText;
    ImageView imageView;
    Intent outIntent;
    Uri imageUri;
    FirebaseStorage storage;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
//        goToLogin();
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.loginBtn).setOnClickListener(this);
        newWork();

    }
// ***************************IMAGES*********************************
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
            uploadPicture();
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