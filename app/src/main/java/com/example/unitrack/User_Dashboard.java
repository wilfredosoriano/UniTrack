package com.example.unitrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class User_Dashboard extends AppCompatActivity {

    //Button BtnLogout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        //BtnLogout=findViewById(R.id.BtnLogout);
        mAuth = FirebaseAuth.getInstance();

       // BtnLogout.setOnClickListener(view -> {
        //    mAuth.signOut();
          //  startActivity(new Intent(User_Dashboard.this, Login.class));
        //});
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user==null){
            startActivity(new Intent(User_Dashboard.this, Login.class));
        }
    }
    public void Profile(View view) {
        Intent intent= new Intent(User_Dashboard.this, Profile.class);
        startActivity(intent);
    }
}