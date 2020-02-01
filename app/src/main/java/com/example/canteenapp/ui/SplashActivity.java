package com.example.canteenapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.canteenapp.Logout;
import com.example.canteenapp.model.Account;
import com.example.canteenapp.ui.mess.MessMainActivity;
import com.example.canteenapp.ui.student.StudentMainActivity;
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

    private FirebaseAuth mAuth;
    private Class nextClass = null, mainClass = null;
    private String role;
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
        Logout.init(getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(SplashActivity.this, ChoiceActivity.class));
            finish();
        } else {
            Log.d(TAG, "no firebase user logged in");

            role = getIntent().getStringExtra("role");
            setClasses();

            RegistrationCheck();
        }
    }

    private void RegistrationCheck() {
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("users/" + mAuth.getCurrentUser().getUid() + "/account");
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Account account = dataSnapshot.getValue(Account.class);
                if (account != null )
                    if ((role == null || role.equals("")))
                        role = account.getRole();
                    else if (!role.equals(account.getRole())) {
                        Toast.makeText(SplashActivity.this, "This account is associated with other role.", Toast.LENGTH_LONG).show();
                        Logout.FirebaseSignOut();
                        Logout.GoogleSignOut();
                        return;
                    }
                setClasses();

                if (account != null && account.getRegistered() == Boolean.TRUE && mainClass != null)
                    startActivity(new Intent(SplashActivity.this, mainClass));

                else if (nextClass != null)
                    startActivity(new Intent(SplashActivity.this, nextClass));

                else {
                    Log.d(TAG, "not registered and no role");
                    Toast.makeText(SplashActivity.this, "Login error.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SplashActivity.this, SplashActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    Logout.CompleteSignOut();
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

    private void setClasses() {

        if (role == null || role.equals("")) {
            nextClass = null;
            mainClass = null;
        }
        else if (role.equals("student")) {
            nextClass = RegisterStudent.class;
            mainClass = StudentMainActivity.class;
        }
        else if (role.equals("mess")) {
            nextClass = RegisterMess.class;
            mainClass = MessMainActivity.class;
        }
    }
}
