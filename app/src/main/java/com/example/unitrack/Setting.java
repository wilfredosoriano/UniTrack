package com.example.unitrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Setting extends AppCompatActivity {

    ImageView BtnLogout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        BtnLogout = findViewById(R.id.BtnLogout);
        mAuth=FirebaseAuth.getInstance();

        BtnLogout.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(Setting.this, Login.class));
        });
    }

    public void BackSet(View view) {
        Intent intent = new Intent(Setting.this, Profile.class);
        startActivity(intent);
    }
}