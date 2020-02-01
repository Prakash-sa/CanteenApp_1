package com.example.canteenapp.ui.student.registerstudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.canteenapp.R;
import com.example.canteenapp.model.Account;
import com.example.canteenapp.ui.mess.MessMainActivity;
import com.example.canteenapp.ui.mess.registermess.RegisterMess;
import com.example.canteenapp.ui.student.StudentMainActivity;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterStudent extends AppCompatActivity {
    FirebaseUser user = null;
    private TextView name, email,nav_email,nav_name;
    private EditText messID;
    private ImageView dp,nav_image;
    private final String TAG = "RegisterStudent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        user = FirebaseAuth.getInstance().getCurrentUser();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        messID = findViewById(R.id.mess_id);
        dp = findViewById(R.id.image);

        if (user != null) {
            name.setText(user.getDisplayName());
            email.setText(user.getEmail());
            dp.setImageURI(user.getPhotoUrl());


        } else {
            Toast.makeText(this, "Something is not right. Please restart the app.", Toast.LENGTH_LONG).show();
        }
    }


    public void saveDetails(View v) {
        String messid = messID.getText().toString();
        if (messid.equals("")) {
            messID.setError("Required");
        } else {
            // show progress
            ((LinearLayout) findViewById(R.id.registration_done)).setVisibility(View.VISIBLE);

            // save the details to firebase
            DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("users/" + user.getUid() + "/account");
            Account account = new Account();

            account.setMessid(messid);
            account.setRegistered(Boolean.TRUE);
            account.setRole("student");

            dbref.setValue(account).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    startActivity(new Intent(RegisterStudent.this, StudentMainActivity.class));
                    finish();
                    Log.d(TAG, "registered");
                }
            }).addOnCanceledListener(new OnCanceledListener() {
                @Override
                public void onCanceled() {
                    ((LinearLayout) findViewById(R.id.registration_done)).setVisibility(View.GONE);
                    Toast.makeText(RegisterStudent.this, "Registration cancelled", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "cancelled");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    ((LinearLayout) findViewById(R.id.registration_done)).setVisibility(View.GONE);
                    Toast.makeText(RegisterStudent.this, "Failed: ", Toast.LENGTH_LONG).show();
                    Log.d(TAG, e.getMessage());
                }
            });
        }
    }
}
