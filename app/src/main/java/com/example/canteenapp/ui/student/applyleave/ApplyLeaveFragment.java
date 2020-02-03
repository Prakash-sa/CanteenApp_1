package com.example.canteenapp.ui.student.applyleave;

import android.app.AlarmManager;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.canteenapp.R;

import java.util.Date;

public class ApplyLeaveFragment extends Fragment {

    private int STATE = 0;
    private long startDate = 0, endDate = 0;
    private final String TAG = "ApplyLeave";
    private View root;

    private ApplyLeaveViewModel applyLeaveViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        applyLeaveViewModel =
                ViewModelProviders.of(this).get(ApplyLeaveViewModel.class);
        root = inflater.inflate(R.layout.fragment_apply_leave, container, false);

        CalendarView calendarView = root.findViewById(R.id.select_date);
        CardView leave = root.findViewById(R.id.apply_leave);


        // initialise calendar
        calendarView.setDate((new Date()).getTime() - AlarmManager.INTERVAL_DAY);
        calendarView.setMinDate((new Date()).getTime());
        calendarView.setMaxDate((new Date()).getTime() + 28 * AlarmManager.INTERVAL_DAY);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if (STATE % 2 == 0) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month, dayOfMonth);
                    startDate = (calendar.getTimeInMillis());
                    STATE = (STATE + 1) % 2;
                    setStart();
                    Toast.makeText(getContext(), "Now select the end date", Toast.LENGTH_LONG).show();
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month, dayOfMonth);
                    endDate = (calendar.getTimeInMillis());
                    STATE = (STATE + 1) % 2;
                    setEnd();
                    colorRange();
                }
            }
        });

        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startDate != 0 && endDate != 0) {
                    // TODO: save to database
                } else {
                    Toast.makeText(getContext(), "Select the start and end date", Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;
    }

    private void setStart() {
        ((TextView) root.findViewById(R.id.end_date)).setText("--/--/----");
        ((TextView) root.findViewById(R.id.start_date)).setText(SimpleDateFormat.getDateInstance().format(startDate));
        Log.d(TAG, "setStart: "+new Date(startDate).toString());

    }

    private void setEnd() {

        ((TextView) root.findViewById(R.id.end_date)).setText(SimpleDateFormat.getDateInstance().format(endDate));
    }

    private void colorRange() {
        if (startDate != 0 && endDate != 0 && STATE == 0) {
//            calendarView;
        }
    }
}