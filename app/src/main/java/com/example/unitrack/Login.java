package com.example.unitrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    EditText etRegEmailAdd, etRegPassword;
    Button tvRegisterHere, BtnLogin;

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etRegEmailAdd = findViewById(R.id.etRegEmailAdd);
        etRegPassword = findViewById(R.id.etRegPassword);
        tvRegisterHere = findViewById(R.id.tvRegisterHere);
        BtnLogin = findViewById(R.id.BtnLogin);

        mAuth=FirebaseAuth.getInstance();
        mDatabase =FirebaseDatabase.getInstance();

        BtnLogin.setOnClickListener(view -> {
            loginUser();
        });
        tvRegisterHere.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, Register.class));
        });

    }

    private void loginUser() {
        String email = etRegEmailAdd.getText().toString().trim();
        String password = etRegPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            etRegEmailAdd.setError("Please provide an email");
            etRegEmailAdd.requestFocus();
        } else if (TextUtils.isEmpty(password)){
            etRegPassword.setError("Please provide your password");
            etRegPassword.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Login.this, "User Logged in Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, User_Dashboard.class));
                    }else{
                        Toast.makeText(Login.this, "Registration Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}