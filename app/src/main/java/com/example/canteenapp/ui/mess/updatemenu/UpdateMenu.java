package com.example.canteenapp.ui.mess.updatemenu;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlarmManager;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.canteenapp.R;
import com.example.canteenapp.ui.mess.home.HomeViewModel;
import com.example.canteenapp.ui.mess.updatedatabase.UpdateDatabase;

import java.util.Date;

import static com.example.canteenapp.ui.mess.home.HomeFragment.getCurrentDay;

public class UpdateMenu extends Fragment {

    private LinearLayout cover;
    private final String TAG = "UpdateMenu";
    private long selectedDate = 0;
    private String TYPE = "", TIME = "", DAY = "";
    private final String[] weekName = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private UpdateMenuViewModel mViewModel;

    public static UpdateMenu newInstance() {
        return new UpdateMenu();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel =
                ViewModelProviders.of(this).get(UpdateMenuViewModel.class);
        View root = inflater.inflate(R.layout.update_menu_fragment, container, false);

        CalendarView calendar = root.findViewById(R.id.select_date);
        RadioGroup type = root.findViewById(R.id.type);
        ImageView breakfast = root.findViewById(R.id.breakfast);
        ImageView lunch = root.findViewById(R.id.lunch);
        ImageView dinner = root.findViewById(R.id.dinner);
        cover = root.findViewById(R.id.disable);


        // initialise calendar
        calendar.setDate((new Date()).getTime() - AlarmManager.INTERVAL_DAY);
        calendar.setMinDate((new Date()).getTime());
        calendar.setMaxDate((new Date()).getTime() + 28 * AlarmManager.INTERVAL_DAY);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                Log.d(TAG, "Day-text : " + weekName[dayOfWeek]);
                DAY = weekName[dayOfWeek];
                selectedDate = view.getDate();
                enableButtons();
            }
        });

        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TIME = "Breakfast";
                gotoEdit();
            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TIME = "Lunch";
                gotoEdit();
            }
        });

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TIME = "Dinner";
                gotoEdit();
            }
        });

        type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.meal_radio)
                    TYPE = "menu";
                else if (checkedId == R.id.extra_radio)
                    TYPE = "extra";
                enableButtons();
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UpdateMenuViewModel.class);

        // TODO: Use the ViewModel
    }

    private void gotoEdit() {
        startActivity(new Intent(getContext(), UpdateDatabase.class)
                .putExtra("type", TYPE)
                .putExtra("day", DAY).putExtra("time", TIME));
    }

    private void enableButtons() {
        if (!DAY.equals("") && !TYPE.equals(""))
            cover.setVisibility(View.GONE);
        else
            cover.setVisibility(View.VISIBLE);
    }

}
