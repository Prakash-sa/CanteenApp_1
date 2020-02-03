package com.example.canteenapp.ui.student.feedback;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.canteenapp.R;
import com.example.canteenapp.model.Feedback;
import com.example.canteenapp.ui.student.StudentMainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FeedbackFragment extends Fragment {

    private FeedbackViewModel feedbackViewModel;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Students").child("Feedback");
    private HashMap<String,String>radioanswer=new HashMap<String,String>();

    private View root;
    private Context context;
    private EditText feedback;
    private TextView charCount;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        feedbackViewModel =
                ViewModelProviders.of(this).get(FeedbackViewModel.class);
        root = inflater.inflate(R.layout.fragment_feedback, container, false);
        context = root.getContext();

        ((TextView) root.findViewById(R.id.mail)).setText(StudentMainActivity.user.getEmail());
        charCount = (TextView) root.findViewById(R.id.char_count);
        feedback = (EditText) root.findViewById(R.id.feedback);
        CardView submit = (CardView) root.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedback();
            }
        });

        feedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int l = s.length();
                charCount.setText("" + l);
                if (l > 400) ((LinearLayout) feedback.getParent()).setBackgroundResource(R.drawable.border_error);
                else ((LinearLayout) feedback.getParent()).setBackgroundResource(R.drawable.border_normal);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return root;
    }

    private void sendFeedback() {

        String email = ((TextView) root.findViewById(R.id.mail)).getText().toString();
        String feed = feedback.getText().toString();

        if (feed.equals("")) {
            ((EditText) root.findViewById(R.id.feedback)).setError("You are forgetting the feedback!");
            return;
        }

        if (feed.length() > 400) {
            feedback.setError("Your feedback is large. Remove some text");
            return;
        }

        radioanswer.clear();
        radioButton();
        Feedback feedback=new Feedback(feed,radioanswer);

        myRef.push().setValue(feedback);
    }

    private void radioButton(){

        radioGroup=root.findViewById(R.id.radio_answer_1);
        int selectedid=radioGroup.getCheckedRadioButtonId();
        radioButton=root.findViewById(selectedid);
        radioanswer.put("Food Quality",radioButton.getText().toString());


        radioGroup=root.findViewById(R.id.radio_answer_2);
        selectedid=radioGroup.getCheckedRadioButtonId();
        radioButton=root.findViewById(selectedid);
        radioanswer.put("Food Quantity",radioButton.getText().toString());

        radioGroup=root.findViewById(R.id.radio_answer_2);
        selectedid=radioGroup.getCheckedRadioButtonId();
        radioButton=root.findViewById(selectedid);
        radioanswer.put("Mess are Hygienic",radioButton.getText().toString());

        radioGroup=root.findViewById(R.id.radio_answer_2);
        selectedid=radioGroup.getCheckedRadioButtonId();
        radioButton=root.findViewById(selectedid);
        radioanswer.put("Food Hygienic",radioButton.getText().toString());

        radioGroup=root.findViewById(R.id.radio_answer_2);
        selectedid=radioGroup.getCheckedRadioButtonId();
        radioButton=root.findViewById(selectedid);
        radioanswer.put("Utensils Hygienic",radioButton.getText().toString());

        radioGroup=root.findViewById(R.id.radio_answer_2);
        selectedid=radioGroup.getCheckedRadioButtonId();
        radioButton=root.findViewById(selectedid);
        radioanswer.put("Food Price",radioButton.getText().toString());


    }
}