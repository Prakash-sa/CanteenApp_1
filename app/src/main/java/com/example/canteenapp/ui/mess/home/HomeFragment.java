package com.example.canteenapp.ui.mess.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.canteenapp.R;
import com.example.canteenapp.model.MessDatabaseExtrasLunch;
import com.example.canteenapp.model.MessDatabaseMenuLunch;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10;
    private TextView extratextView1,extratextView2,extratextView3,extratextView4,extratextView5,extratextView6,extratextView7,extratextView8,extratextView9,extratextView10;
    private String today;
    private MessDatabaseMenuLunch messDatabaseMenuLunch;
    private MessDatabaseExtrasLunch messDatabaseExtrasLunch;
    private HomeViewModel homeViewModel;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Mess");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mess_home, container, false);

        today=getCurrentDay();
        textView1=root.findViewById(R.id.home_menu_textview_1);
        textView2=root.findViewById(R.id.home_menu_textview_2);
        textView3=root.findViewById(R.id.home_menu_textview_3);
        textView4=root.findViewById(R.id.home_menu_textview_4);
        textView5=root.findViewById(R.id.home_menu_textview_5);
        textView6=root.findViewById(R.id.home_menu_textview_6);
        textView7=root.findViewById(R.id.home_menu_textview_7);
        textView8=root.findViewById(R.id.home_menu_textview_8);
        textView9=root.findViewById(R.id.home_menu_textview_9);
        textView10=root.findViewById(R.id.home_menu_textview_10);

        extratextView1=root.findViewById(R.id.home_extra_textview_1);
        extratextView2=root.findViewById(R.id.home_extra_textview_2);
        extratextView3=root.findViewById(R.id.home_extra_textview_3);
        extratextView4=root.findViewById(R.id.home_extra_textview_4);
        extratextView5=root.findViewById(R.id.home_extra_textview_5);
        extratextView6=root.findViewById(R.id.home_extra_textview_6);
        extratextView7=root.findViewById(R.id.home_extra_textview_7);
        extratextView8=root.findViewById(R.id.home_extra_textview_8);
        extratextView9=root.findViewById(R.id.home_extra_textview_9);
        extratextView10=root.findViewById(R.id.home_extra_textview_10);


        myRef.child("menu").child("Monday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messDatabaseMenuLunch =dataSnapshot.getValue(MessDatabaseMenuLunch.class);
                setTextViewMenu();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("Firebase Error","Error of firebase");
            }
        });
        myRef.child("extra").child("Monday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messDatabaseExtrasLunch =dataSnapshot.getValue(MessDatabaseExtrasLunch.class);
                setTextViewExtra();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return root;
    }

    public static String getCurrentDay(){
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        Calendar calendar = Calendar.getInstance();
        return dayFormat.format(calendar.getTime());

    }

    private void setTextViewMenu(){
        textView1.setText(messDatabaseMenuLunch.getChapatiType());
        textView2.setText(messDatabaseMenuLunch.getRiceType());
        textView3.setText(messDatabaseMenuLunch.getSaladType());
        textView4.setText(messDatabaseMenuLunch.getSabjiType());
        textView5.setText(messDatabaseMenuLunch.getDallType());
        textView6.setText(messDatabaseMenuLunch.getCurdType());
        textView7.setText(messDatabaseMenuLunch.getOptional1());
        textView8.setText(messDatabaseMenuLunch.getOptional2());
        textView9.setText(messDatabaseMenuLunch.getOptional3());
        textView10.setText(messDatabaseMenuLunch.getOptional4());

    }
    private void setTextViewExtra(){
        extratextView1.setText(messDatabaseExtrasLunch.getGheeType());
        extratextView2.setText(messDatabaseExtrasLunch.getSweetType());
        extratextView3.setText(messDatabaseExtrasLunch.getJuiceType());
        extratextView4.setText(messDatabaseExtrasLunch.getIceCreamType());
        extratextView5.setText(messDatabaseExtrasLunch.getOptional1());
        extratextView6.setText(messDatabaseExtrasLunch.getOptional2());
        extratextView7.setText(messDatabaseExtrasLunch.getOptional3());
        extratextView8.setText(messDatabaseExtrasLunch.getOptional4());
        extratextView9.setText(messDatabaseExtrasLunch.getOptional5());
        extratextView10.setText(messDatabaseExtrasLunch.getOptional6());
    }
}