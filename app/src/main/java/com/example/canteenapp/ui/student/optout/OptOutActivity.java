package com.example.canteenapp.ui.student.optout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.canteenapp.R;

import java.util.Date;
import java.util.Locale;

import static android.icu.text.DateFormat.getDateInstance;

public class OptOutActivity extends AppCompatActivity {
    private TextView setDay, breakfastStatus, lunchStatus, dinnerStatus;
    private TextView breakfastCount, lunchCount, dinnerCount;
    private CheckBox breakfast, lunch, dinner;
    private ImageView prevDay, nextDay;

    private String today;
    private long baseTime;
    private final String TAG = "OptOut";

    private View.OnClickListener prev, next, curr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opt_out);

        setDay = findViewById(R.id.cur_day);
        prevDay = findViewById(R.id.prev_day);
        nextDay = findViewById(R.id.next_day);
        breakfastStatus = findViewById(R.id.breakfast_status);
        lunchStatus = findViewById(R.id.lunch_status);
        dinnerStatus = findViewById(R.id.dinner_status);
        breakfastCount = findViewById(R.id.breakfast_count);
        lunchCount = findViewById(R.id.lunch_count);
        dinnerCount = findViewById(R.id.dinner_count);
        breakfast = findViewById(R.id.breakfast);
        lunch = findViewById(R.id.lunch);
        dinner = findViewById(R.id.dinner);

        curr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        };

        prev = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat df = SimpleDateFormat.getDateInstance();

                Date now = new Date();
                long millisToday = (now.getHours()*3600 + now.getMinutes()*60 + now.getSeconds()) * 1000;
                baseTime = now.getTime() - millisToday;

                setDay.setText(df.format(baseTime));
                prevDay.setVisibility(View.GONE);
                nextDay.setOnClickListener(curr);
                reinit();
            }
        };

        next = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat df = SimpleDateFormat.getDateInstance();

                Date now = new Date();
                long millisToday = (now.getHours()*3600 + now.getMinutes()*60 + now.getSeconds()) * 1000;
                baseTime = now.getTime() - millisToday + 2*AlarmManager.INTERVAL_DAY;

                setDay.setText(df.format(baseTime));
                nextDay.setVisibility(View.GONE);
                prevDay.setOnClickListener(curr);
                reinit();
            }
        };

        setDate();

    }

    @Override
    public void onResume() {
        super.onResume();
//        Date t = new Date();
//        long millisToday = (t.getHours()*3600 + t.getMinutes()*60 + t.getSeconds()) * 1000;
//
//        baseTime = t.getTime() - millisToday;
        Log.d(TAG, "onResume: " + new Date(baseTime).toString());
//        baseTime = ;
        reinit();
    }

    private void setDate() {
        // update values/details for the shown date

        Date now = new Date();
        long millisToday = (now.getHours()*3600 + now.getMinutes()*60 + now.getSeconds()) * 1000;
        baseTime = now.getTime() - millisToday + AlarmManager.INTERVAL_DAY;

        DateFormat sd = getDateInstance();
        setDay.setText(sd.format(baseTime));
        prevDay.setVisibility(View.VISIBLE);
        nextDay.setVisibility(View.VISIBLE);
        prevDay.setOnClickListener(prev);
        nextDay.setOnClickListener(next);
        reinit();
    }

    private void reinit() {
        // get
        today = (new SimpleDateFormat("EEEE", Locale.getDefault())).format(baseTime);
        // getfromfirebase();
        breakfast.setChecked(false);
        lunch.setChecked(false);
        dinner.setChecked(false);
        setStatus();
    }

    private void setStatus() {
        long[] mealTime = {9*3600*1000, 14*3600*1000, 21*3600*1000};
        long currTime = new Date().getTime();
        if (baseTime + mealTime[0] - currTime > AlarmManager.INTERVAL_DAY) {
            breakfastStatus.setText("ONGOING");
            breakfast.setEnabled(true);
        } else {
            breakfastStatus.setText("FINAL");
            breakfast.setEnabled(false);
        }

        if (baseTime + mealTime[1] - currTime > AlarmManager.INTERVAL_DAY) {
            lunchStatus.setText("ONGOING");
            lunch.setEnabled(true);
        } else {
            lunchStatus.setText("FINAL");
            lunch.setEnabled(false);
        }

        if (baseTime + mealTime[2] - currTime > AlarmManager.INTERVAL_DAY) {
            dinnerStatus.setText("ONGOING");
            dinner.setEnabled(true);
        } else {
            dinnerStatus.setText("FINAL");
            dinner.setEnabled(false);
        }

    }


}
