package com.example.canteenapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.canteenapp.MainActivity;
import com.example.canteenapp.ui.mess.registermess.RegisterMess;
import com.example.canteenapp.ui.student.registerstudent.RegisterStudent;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    Class nextClass = null;
    private final String TAG = "Splash";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(SplashActivity.this, ChoiceActivity.class));
            finish();
        } else {
            Log.d(TAG, "no firebase use logged in");

            String role = getIntent().getStringExtra("role");
            if (role == null || role.equals("")) {
                nextClass = null;
            }
            else if (role.equals("student")) {
                nextClass = RegisterStudent.class;
            }
            else if (role.equals("mess")) {
                nextClass = RegisterMess.class;
            }

            RegistrationCheck();
        }
    }

    private void RegistrationCheck() {
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("users/" + mAuth.getCurrentUser().getUid() + "/account");
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean status = dataSnapshot.getValue(Boolean.class);
                if (status == Boolean.TRUE)
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));

                else if (nextClass != null)
                    startActivity(new Intent(SplashActivity.this, nextClass));

                else {
                    Log.d(TAG, "not registered and no role");
                    Toast.makeText(SplashActivity.this, "Login error.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SplashActivity.this, SplashActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    mAuth.signOut();
                }
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "fetch cancelled.");
                Toast.makeText(SplashActivity.this, "Login cancelled", Toast.LENGTH_LONG).show();
            }
        });
    }
}
