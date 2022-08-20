package com.example.restaurantmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignIn extends AppCompatActivity {

    EditText Email, Password;
    String email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Email = findViewById(R.id.loginEmail);
        Password = findViewById(R.id.loginPassword);
    }

    private void login(){
        email = Email.getText().toString().trim();
        password = Password.getText().toString().trim();
        if(email.isEmpty()){
            Email.setError("Email is nor correct");
            Email.requestFocus();
        }else if(password.isEmpty()){
            Password.setError("Password is nor correct");
            Password.requestFocus();
        }else{

        }
    }
}