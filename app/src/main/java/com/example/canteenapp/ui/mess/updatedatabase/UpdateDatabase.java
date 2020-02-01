package com.example.canteenapp.ui.mess.updatedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.canteenapp.R;
import com.example.canteenapp.model.MessDatabaseExtras;
import com.example.canteenapp.model.MessDatabaseMenu;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDatabase extends AppCompatActivity {

    private MessDatabaseMenu messDatabaseMenu;
    private MessDatabaseExtras messDatabaseExtras;
    private Button submit_bt;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Mess");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_database);

        submit_bt=findViewById(R.id.mess_schedule_submit);

        final String type=getIntent().getStringExtra("type");
        final String day=getIntent().getStringExtra("day");


        submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type=="menu"){
                    messDatabaseMenu =new MessDatabaseMenu();
                    myRef.child(type).child(day).setValue(messDatabaseMenu);
                    finish();
                }
                if(type=="extra"){
                    messDatabaseExtras=new MessDatabaseExtras();
                    myRef.child(type).child(day).setValue(messDatabaseExtras);
                }
            }
        });

    }
}
