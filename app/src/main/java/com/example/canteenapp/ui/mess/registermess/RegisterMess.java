package com.example.canteenapp.ui.mess.registermess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.canteenapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterMess extends AppCompatActivity {
    FirebaseUser user = null;
    TextView name, email;
    EditText messID;
    ImageView dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_mess);

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
            // save the details to firebase
            Toast.makeText(this, "TODO", Toast.LENGTH_LONG).show();
        }
    }
}
