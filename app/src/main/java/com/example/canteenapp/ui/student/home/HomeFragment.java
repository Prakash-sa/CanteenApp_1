package com.example.canteenapp.ui.student.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.canteenapp.R;
import com.example.canteenapp.model.MessDatabaseExtrasBreakfast;
import com.example.canteenapp.model.MessDatabaseExtrasDinner;
import com.example.canteenapp.model.MessDatabaseExtrasLunch;
import com.example.canteenapp.model.MessDatabaseMenuBreakfast;
import com.example.canteenapp.model.MessDatabaseMenuDinner;
import com.example.canteenapp.model.MessDatabaseMenuLunch;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {


    private MessDatabaseMenuLunch messDatabaseMenuLunch;
    private MessDatabaseMenuBreakfast messDatabaseMenuBreakfast;
    private MessDatabaseMenuDinner messDatabaseMenuDinner;
    private MessDatabaseExtrasBreakfast messDatabaseExtrasBreakfast;
    private MessDatabaseExtrasDinner messDatabaseExtrasDinner;
    private MessDatabaseExtrasLunch messDatabaseExtrasLunch;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Mess");
    private String today;
    private ListView breakfast_menu_listView,lunch_menu_listView,dinner_menu_listView,breakfast_extra_listView,lunch_extra_listView,dinner_extra_listView;
    private HomeViewModel homeViewModel;
    ArrayAdapter<String>adapter1,adapter2,adapter3,adapter4,adapter5,adapter6;
    private List<String>items1,items2,items3,items4,items5,items6;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        items1.add("asdf");
        today=getCurrentDay();
        breakfast_menu_listView=root.findViewById(R.id.menu_breakfast_listview);
        lunch_menu_listView=root.findViewById(R.id.menu_lunch_listview);
        dinner_menu_listView=root.findViewById(R.id.menu_dinner_listview);

        breakfast_extra_listView=root.findViewById(R.id.extra_breakfast_listview);
        lunch_extra_listView=root.findViewById(R.id.extra_lunch_listview);
        dinner_extra_listView=root.findViewById(R.id.extra_dinner_listview);

        getfromfirabase();
        additems();
        setAdapterforlist();

        return root;
    }

    private void getfromfirabase(){
        myRef.child("Mess").child("menu").child(today).child("Breakfast").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messDatabaseMenuBreakfast=dataSnapshot.getValue(MessDatabaseMenuBreakfast.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRef.child("Mess").child("menu").child(today).child("Lunch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messDatabaseMenuLunch=dataSnapshot.getValue(MessDatabaseMenuLunch.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef.child("Mess").child("menu").child(today).child("Dinner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messDatabaseMenuDinner=dataSnapshot.getValue(MessDatabaseMenuDinner.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef.child("Mess").child("extra").child(today).child("Breakfast").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messDatabaseExtrasBreakfast=dataSnapshot.getValue(MessDatabaseExtrasBreakfast.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRef.child("Mess").child("extra").child(today).child("Lunch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messDatabaseExtrasLunch=dataSnapshot.getValue(MessDatabaseExtrasLunch.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRef.child("Mess").child("extra").child(today).child("Dinner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messDatabaseExtrasDinner=dataSnapshot.getValue(MessDatabaseExtrasDinner.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void additems(){
        if(messDatabaseMenuBreakfast.getChapatiType()!=null)items1.add(messDatabaseMenuBreakfast.getChapatiType());
        if(messDatabaseMenuBreakfast.getRiceType()!=null)items1.add(messDatabaseMenuBreakfast.getRiceType());
        if(messDatabaseMenuBreakfast.getSabjiType()!=null)items1.add(messDatabaseMenuBreakfast.getSabjiType());
        if(messDatabaseMenuBreakfast.getSaladType()!=null)items1.add(messDatabaseMenuBreakfast.getSaladType());
        if(messDatabaseMenuBreakfast.getDallType()!=null)items1.add(messDatabaseMenuBreakfast.getDallType());
        if(messDatabaseMenuBreakfast.getCurdType()!=null)items1.add(messDatabaseMenuBreakfast.getCurdType());
        if(messDatabaseMenuBreakfast.getOptional1()!=null)items1.add(messDatabaseMenuBreakfast.getOptional1());
        if(messDatabaseMenuBreakfast.getOptional2()!=null)items1.add(messDatabaseMenuBreakfast.getOptional2());
        if(messDatabaseMenuBreakfast.getOptional3()!=null)items1.add(messDatabaseMenuBreakfast.getOptional3());
        if(messDatabaseMenuBreakfast.getOptional4()!=null)items1.add(messDatabaseMenuBreakfast.getOptional4());


        if(messDatabaseMenuLunch.getChapatiType()!=null)items2.add(messDatabaseMenuLunch.getChapatiType());
        if(messDatabaseMenuLunch.getRiceType()!=null)items2.add(messDatabaseMenuLunch.getRiceType());
        if(messDatabaseMenuLunch.getSabjiType()!=null)items2.add(messDatabaseMenuLunch.getSabjiType());
        if(messDatabaseMenuLunch.getSaladType()!=null)items2.add(messDatabaseMenuLunch.getSaladType());
        if(messDatabaseMenuLunch.getDallType()!=null)items2.add(messDatabaseMenuLunch.getDallType());
        if(messDatabaseMenuLunch.getCurdType()!=null)items2.add(messDatabaseMenuLunch.getCurdType());
        if(messDatabaseMenuLunch.getOptional1()!=null)items2.add(messDatabaseMenuLunch.getOptional1());
        if(messDatabaseMenuLunch.getOptional2()!=null)items2.add(messDatabaseMenuLunch.getOptional2());
        if(messDatabaseMenuLunch.getOptional3()!=null)items2.add(messDatabaseMenuLunch.getOptional3());
        if(messDatabaseMenuLunch.getOptional4()!=null)items2.add(messDatabaseMenuLunch.getOptional4());

        if(messDatabaseMenuDinner.getChapatiType()!=null)items3.add(messDatabaseMenuDinner.getChapatiType());
        if(messDatabaseMenuDinner.getRiceType()!=null)items3.add(messDatabaseMenuDinner.getRiceType());
        if(messDatabaseMenuDinner.getSabjiType()!=null)items3.add(messDatabaseMenuDinner.getSabjiType());
        if(messDatabaseMenuDinner.getSaladType()!=null)items3.add(messDatabaseMenuDinner.getSaladType());
        if(messDatabaseMenuDinner.getDallType()!=null)items3.add(messDatabaseMenuDinner.getDallType());
        if(messDatabaseMenuDinner.getCurdType()!=null)items3.add(messDatabaseMenuDinner.getCurdType());
        if(messDatabaseMenuDinner.getOptional1()!=null)items3.add(messDatabaseMenuDinner.getOptional1());
        if(messDatabaseMenuDinner.getOptional2()!=null)items3.add(messDatabaseMenuDinner.getOptional2());
        if(messDatabaseMenuDinner.getOptional3()!=null)items3.add(messDatabaseMenuDinner.getOptional3());
        if(messDatabaseMenuDinner.getOptional4()!=null)items3.add(messDatabaseMenuDinner.getOptional4());

        if(messDatabaseExtrasBreakfast.getGheeType()!=null)items4.add(messDatabaseExtrasBreakfast.getGheeType());
        if(messDatabaseExtrasBreakfast.getSweetType()!=null)items4.add(messDatabaseExtrasBreakfast.getSweetType());
        if(messDatabaseExtrasBreakfast.getJuiceType()!=null)items4.add(messDatabaseExtrasBreakfast.getJuiceType());
        if(messDatabaseExtrasBreakfast.getIceCreamType()!=null)items4.add(messDatabaseExtrasBreakfast.getIceCreamType());
        if(messDatabaseExtrasBreakfast.getOptional5()!=null)items4.add(messDatabaseExtrasBreakfast.getOptional5());
        if(messDatabaseExtrasBreakfast.getOptional6()!=null)items4.add(messDatabaseExtrasBreakfast.getOptional6());
        if(messDatabaseExtrasBreakfast.getOptional1()!=null)items4.add(messDatabaseExtrasBreakfast.getOptional1());
        if(messDatabaseExtrasBreakfast.getOptional2()!=null)items4.add(messDatabaseExtrasBreakfast.getOptional2());
        if(messDatabaseExtrasBreakfast.getOptional3()!=null)items4.add(messDatabaseExtrasBreakfast.getOptional3());
        if(messDatabaseExtrasBreakfast.getOptional4()!=null)items4.add(messDatabaseExtrasBreakfast.getOptional4());

        if(messDatabaseExtrasLunch.getGheeType()!=null)items5.add(messDatabaseExtrasLunch.getGheeType());
        if(messDatabaseExtrasLunch.getSweetType()!=null)items5.add(messDatabaseExtrasLunch.getSweetType());
        if(messDatabaseExtrasLunch.getJuiceType()!=null)items5.add(messDatabaseExtrasLunch.getJuiceType());
        if(messDatabaseExtrasLunch.getIceCreamType()!=null)items5.add(messDatabaseExtrasLunch.getIceCreamType());
        if(messDatabaseExtrasLunch.getOptional5()!=null)items5.add(messDatabaseExtrasLunch.getOptional5());
        if(messDatabaseExtrasLunch.getOptional6()!=null)items5.add(messDatabaseExtrasLunch.getOptional6());
        if(messDatabaseExtrasLunch.getOptional1()!=null)items5.add(messDatabaseExtrasLunch.getOptional1());
        if(messDatabaseExtrasLunch.getOptional2()!=null)items5.add(messDatabaseExtrasLunch.getOptional2());
        if(messDatabaseExtrasLunch.getOptional3()!=null)items5.add(messDatabaseExtrasLunch.getOptional3());
        if(messDatabaseExtrasLunch.getOptional4()!=null)items5.add(messDatabaseExtrasLunch.getOptional4());

        if(messDatabaseExtrasDinner.getGheeType()!=null)items6.add(messDatabaseExtrasDinner.getGheeType());
        if(messDatabaseExtrasDinner.getSweetType()!=null)items6.add(messDatabaseExtrasDinner.getSweetType());
        if(messDatabaseExtrasDinner.getJuiceType()!=null)items6.add(messDatabaseExtrasDinner.getJuiceType());
        if(messDatabaseExtrasDinner.getIceCreamType()!=null)items6.add(messDatabaseExtrasDinner.getIceCreamType());
        if(messDatabaseExtrasDinner.getOptional5()!=null)items6.add(messDatabaseExtrasDinner.getOptional5());
        if(messDatabaseExtrasDinner.getOptional6()!=null)items6.add(messDatabaseExtrasDinner.getOptional6());
        if(messDatabaseExtrasDinner.getOptional1()!=null)items6.add(messDatabaseExtrasDinner.getOptional1());
        if(messDatabaseExtrasDinner.getOptional2()!=null)items6.add(messDatabaseExtrasDinner.getOptional2());
        if(messDatabaseExtrasDinner.getOptional3()!=null)items6.add(messDatabaseExtrasDinner.getOptional3());
        if(messDatabaseExtrasDinner.getOptional4()!=null)items6.add(messDatabaseExtrasDinner.getOptional4());

    }

    private void setAdapterforlist(){
        adapter1=new ArrayAdapter<>(getContext(),R.layout.listview_food,R.id.food_menu_textview,items1);
        breakfast_menu_listView.setAdapter(adapter1);
        adapter2=new ArrayAdapter<>(getContext(),R.layout.listview_food,R.id.food_menu_textview,items2);
        lunch_menu_listView.setAdapter(adapter2);
        adapter3=new ArrayAdapter<>(getContext(),R.layout.listview_food,R.id.food_menu_textview,items3);
        dinner_menu_listView.setAdapter(adapter3);

        adapter4=new ArrayAdapter<>(getContext(),R.layout.listview_food,R.id.food_menu_textview,items4);
        breakfast_extra_listView.setAdapter(adapter4);
        adapter5=new ArrayAdapter<>(getContext(),R.layout.listview_food,R.id.food_menu_textview,items5);
        lunch_extra_listView.setAdapter(adapter4);
        adapter6=new ArrayAdapter<>(getContext(),R.layout.listview_food,R.id.food_menu_textview,items6);
        dinner_extra_listView.setAdapter(adapter6);

    }

    public static String getCurrentDay(){
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        Calendar calendar = Calendar.getInstance();
        return dayFormat.format(calendar.getTime());

    }

}