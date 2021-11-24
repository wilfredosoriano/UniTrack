package com.example.unitrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference dbReference;

    String userID;
    TextView txtDisplay;
    TextView NameDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        dbReference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        txtDisplay = findViewById(R.id.TextView);
        NameDisplay = findViewById(R.id.Name);

        dbReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null){
                    String id, name, email, address;

                    id = user.id;
                    name = user.name;
                    email = user.email;
                    address = user.address;

                    txtDisplay.setText("Student ID:\n "+id+"\n\nAddress:\n "+address+"\n\nEmail:\n "+email);
                    NameDisplay.setText(name);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "Error", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void Setting(View view) {
        Intent intent = new Intent(Profile.this, Setting.class);
        startActivity(intent);
    }

    public void Back(View view) {
        Intent intent = new Intent(Profile.this, User_Dashboard.class);
        startActivity(intent);
    }
}