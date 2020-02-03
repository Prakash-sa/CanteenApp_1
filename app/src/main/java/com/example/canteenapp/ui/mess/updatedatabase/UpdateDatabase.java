package com.example.canteenapp.ui.mess.updatedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.canteenapp.R;
import com.example.canteenapp.model.MessDatabaseExtrasLunch;
import com.example.canteenapp.model.MessDatabaseMenuLunch;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateDatabase extends AppCompatActivity {


    private EditText price1,price2,price3,price4,price5,price6,price7,price8,price9,price10;

    private EditText editText1,editText2,editText3,editText4,editText5,editText6,editText7,editText8,editText9,editText10;
    private TextView title;
    private String arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10;
    private String argprice1,argprice2,argprice3,argprice4,argprice5,argprice6,argprice7,argprice8,argprice9,argprice10;
    private MessDatabaseMenuLunch messDatabaseMenuLunch;
    private MessDatabaseExtrasLunch messDatabaseExtrasLunch;
    private Button submit_bt;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Mess");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_database);
        setTitle("UpdateMenu");

        submit_bt=findViewById(R.id.mess_schedule_submit);
        title=findViewById(R.id.title_database);
        editText1=findViewById(R.id.databaseedit_text_1);
        editText2=findViewById(R.id.databaseedit_text_2);
        editText3=findViewById(R.id.databaseedit_text_3);
        editText4=findViewById(R.id.databaseedit_text_4);
        editText5=findViewById(R.id.databaseedit_text_5);
        editText6=findViewById(R.id.databaseedit_text_6);
        editText7=findViewById(R.id.databaseedit_text_7);
        editText8=findViewById(R.id.databaseedit_text_8);
        editText9=findViewById(R.id.databaseedit_text_9);
        editText10=findViewById(R.id.databaseedit_text_10);

        price1=findViewById(R.id.priceedit_text_1);
        price2=findViewById(R.id.priceedit_text_2);
        price3=findViewById(R.id.priceedit_text_3);
        price4=findViewById(R.id.priceedit_text_4);
        price5=findViewById(R.id.priceedit_text_5);
        price6=findViewById(R.id.priceedit_text_6);
        price7=findViewById(R.id.priceedit_text_7);
        price8=findViewById(R.id.priceedit_text_8);
        price9=findViewById(R.id.priceedit_text_9);
        price10=findViewById(R.id.priceedit_text_10);

        final String type1=getIntent().getStringExtra("type");
        final String day=getIntent().getStringExtra("day");
        final String timetype=getIntent().getStringExtra("time");
       // title.setText(type1);

        if(type1.equals("menu")){
            myRef.child(type1).child(day).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   messDatabaseMenuLunch =dataSnapshot.getValue(MessDatabaseMenuLunch.class);
                   if(messDatabaseMenuLunch !=null)
                   setEditTextMenu();
                    title.setText("Menu");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if(type1.equals("extra")){
            ((LinearLayout) findViewById(R.id.price_container)).setVisibility(View.VISIBLE);
            myRef.child(type1).child(day).child(timetype).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    messDatabaseExtrasLunch =dataSnapshot.getValue(MessDatabaseExtrasLunch.class);
                    if(messDatabaseExtrasLunch !=null)
                    setEditTextExtra();
                    title.setText("Extra");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }




        submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type1.equals("menu")){
                    if(editText1.getText().toString()!=null)arg1=editText1.getText().toString();
                    else arg1="null";

                    if(editText2.getText().toString()!=null)arg2=editText2.getText().toString();
                    else arg2="null";

                    if(editText3.getText().toString()!=null)arg3=editText3.getText().toString();
                    else arg3="null";

                    if(editText4.getText().toString()!=null)arg4=editText4.getText().toString();
                    else arg4="null";

                    if(editText5.getText().toString()!=null)arg5=editText5.getText().toString();
                    else arg5="null";

                    if(editText6.getText().toString()!=null)arg6=editText6.getText().toString();
                    else arg6="null";

                    if(editText7.getText().toString()!=null)arg7=editText7.getText().toString();
                    else arg7="null";

                    if(editText8.getText().toString()!=null)arg8=editText8.getText().toString();
                    else arg8="null";

                    if(editText9.getText().toString()!=null)arg9=editText9.getText().toString();
                    else arg9="null";

                    if(editText10.getText().toString()!=null)arg10=editText10.getText().toString();
                    else arg10="null";


                    messDatabaseMenuLunch =new MessDatabaseMenuLunch(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
                    myRef.child(type1).child(day).child(timetype).setValue(messDatabaseMenuLunch);
                    Toast.makeText(UpdateDatabase.this,"Updated",Toast.LENGTH_LONG).show();
                }
                if(type1.equals("extra")){

                    if(editText1.getText().toString()!=null)arg1=editText1.getText().toString();
                    else arg1="null";

                    if(editText2.getText().toString()!=null)arg2=editText2.getText().toString();
                    else arg2="null";

                    if(editText3.getText().toString()!=null)arg3=editText3.getText().toString();
                    else arg3="null";

                    if(editText4.getText().toString()!=null)arg4=editText4.getText().toString();
                    else arg4="null";

                    if(editText5.getText().toString()!=null)arg5=editText5.getText().toString();
                    else arg5="null";

                    if(editText6.getText().toString()!=null)arg6=editText6.getText().toString();
                    else arg6="null";

                    if(editText7.getText().toString()!=null)arg7=editText7.getText().toString();
                    else arg7="null";

                    if(editText8.getText().toString()!=null)arg8=editText8.getText().toString();
                    else arg8="null";

                    if(editText9.getText().toString()!=null)arg9=editText9.getText().toString();
                    else arg9="null";

                    if(editText10.getText().toString()!=null)arg10=editText10.getText().toString();
                    else arg10="null";

                    //price

                    if(price1.getText().toString()!=null)arg1=price1.getText().toString();
                    else argprice1="null";

                    if(price2.getText().toString()!=null)argprice2=price2.getText().toString();
                    else argprice2="null";

                    if(price3.getText().toString()!=null)argprice3=price3.getText().toString();
                    else argprice3="null";

                    if(price4.getText().toString()!=null)argprice4=price4.getText().toString();
                    else argprice4="null";

                    if(price5.getText().toString()!=null)argprice5=price5.getText().toString();
                    else argprice5="null";

                    if(price6.getText().toString()!=null)argprice6=price6.getText().toString();
                    else argprice6="null";

                    if(price7.getText().toString()!=null)argprice7=price7.getText().toString();
                    else argprice7="null";

                    if(price8.getText().toString()!=null)argprice8=price8.getText().toString();
                    else argprice8="null";

                    if(price9.getText().toString()!=null)argprice9=price9.getText().toString();
                    else argprice9="null";

                    if(price10.getText().toString()!=null)argprice10=price10.getText().toString();
                    else argprice10="null";

                    messDatabaseExtrasLunch =new MessDatabaseExtrasLunch(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,argprice1,argprice2,argprice3,argprice4,argprice5,argprice6,argprice7,argprice8,argprice9,argprice10);
                    myRef.child(type1).child(day).child(timetype).setValue(messDatabaseExtrasLunch);
                }
            }
        });

    }

    private void setEditTextMenu(){
        editText1.setText(messDatabaseMenuLunch.getChapatiType());
        editText2.setText(messDatabaseMenuLunch.getRiceType());
        editText3.setText(messDatabaseMenuLunch.getSaladType());
        editText4.setText(messDatabaseMenuLunch.getDallType());
        editText5.setText(messDatabaseMenuLunch.getSabjiType());
        editText6.setText(messDatabaseMenuLunch.getCurdType());
        editText7.setHint("Optional");
        editText8.setHint("Optional");
        editText9.setHint("Optional");
        editText10.setHint("Optional");
    }

    private void setEditTextExtra(){
        editText1.setText(messDatabaseExtrasLunch.getGheeType());
        editText2.setText(messDatabaseExtrasLunch.getSweetType());
        editText3.setText(messDatabaseExtrasLunch.getJuiceType());
        editText4.setText(messDatabaseExtrasLunch.getIceCreamType());
        editText5.setHint("Optional");
        editText6.setHint("Optional");
        editText7.setHint("Optional");
        editText8.setHint("Optional");
        editText9.setHint("Optional");
        editText10.setHint("Optional");
    }
}
