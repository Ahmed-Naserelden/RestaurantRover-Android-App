package com.example.restaurantmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class Home extends AppCompatActivity {
    FirebaseStorage storage;
    StorageReference storageReference;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child("images/8afa7801-e6d2-402c-8702-8033e93652dd");
        imageView = findViewById(R.id.imageView4);
        try {
            final File locFile = File.createTempFile("app_ui", "jpg");
            storageReference.getFile(locFile).addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Home.this, "Success download Image", Toast.LENGTH_SHORT).show();
                        Bitmap bitmap = BitmapFactory.decodeFile(locFile.getAbsolutePath());
                        imageView.setImageBitmap(bitmap);
                    }else{
                        Toast.makeText(Home.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (IOException e) {
            Toast.makeText(Home.this, "Catch error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}