package com.example.canteenapp.ui.student.applyleave;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.canteenapp.R;

public class ApplyLeaveFragment extends Fragment {

    private ApplyLeaveViewModel applyLeaveViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        applyLeaveViewModel =
                ViewModelProviders.of(this).get(ApplyLeaveViewModel.class);
        View root = inflater.inflate(R.layout.fragment_apply_leave, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        applyLeaveViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}