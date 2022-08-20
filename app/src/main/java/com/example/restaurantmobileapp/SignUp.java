package com.example.restaurantmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    EditText Email, Password;
    String email, password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.button2).setOnClickListener(this);

    }

    private void registration(){
        email = Email.getText().toString().trim();
        password = Password.getText().toString().trim();

        if(email.isEmpty()){
            Email.setError("Email is nor correct");
            Email.requestFocus();
        }else if(password.isEmpty()){
            Password.setError("Password is nor correct");
            Password.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(SignUp.this, "Email Is Exists Before", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            // Toast.makeText(SignUp.this, "not complete Registration", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            );
        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button2:
                registration();
                break;
        }
    }
}