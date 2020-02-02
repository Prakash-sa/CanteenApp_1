package com.example.canteenapp.ui.mess.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.canteenapp.R;
import com.example.canteenapp.model.MessDatabaseExtras;
import com.example.canteenapp.model.MessDatabaseMenu;
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
    private MessDatabaseMenu messDatabaseMenu;
    private MessDatabaseExtras messDatabaseExtras;
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
                messDatabaseMenu =dataSnapshot.getValue(MessDatabaseMenu.class);
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
                messDatabaseExtras=dataSnapshot.getValue(MessDatabaseExtras.class);
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
        textView1.setText(messDatabaseMenu.getChapatiType());
        textView2.setText(messDatabaseMenu.getRiceType());
        textView3.setText(messDatabaseMenu.getSaladType());
        textView4.setText(messDatabaseMenu.getSabjiType());
        textView5.setText(messDatabaseMenu.getDallType());
        textView6.setText(messDatabaseMenu.getCurdType());
        textView7.setText(messDatabaseMenu.getOptional1());
        textView8.setText(messDatabaseMenu.getOptional2());
        textView9.setText(messDatabaseMenu.getOptional3());
        textView10.setText(messDatabaseMenu.getOptional4());

    }
    private void setTextViewExtra(){
        extratextView1.setText(messDatabaseExtras.getGheeType());
        extratextView2.setText(messDatabaseExtras.getSweetType());
        extratextView3.setText(messDatabaseExtras.getJuiceType());
        extratextView4.setText(messDatabaseExtras.getIceCreamType());
        extratextView5.setText(messDatabaseExtras.getOptional1());
        extratextView6.setText(messDatabaseExtras.getOptional2());
        extratextView7.setText(messDatabaseExtras.getOptional3());
        extratextView8.setText(messDatabaseExtras.getOptional4());
        extratextView9.setText(messDatabaseExtras.getOptional5());
        extratextView10.setText(messDatabaseExtras.getOptional6());
    }
}