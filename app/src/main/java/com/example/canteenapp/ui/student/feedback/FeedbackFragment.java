package com.example.canteenapp.ui.student.feedback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.canteenapp.Adapter.Feedback;
import com.example.canteenapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FeedbackFragment extends Fragment {

    private FeedbackViewModel feedbackViewModel;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Students");
    private HashMap<String,String>radioanswer=new HashMap<String,String>();

    private EditText feedback_text;
    private Button submit_bt;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        feedbackViewModel =
                ViewModelProviders.of(this).get(FeedbackViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_feedback, container, false);
        feedback_text=root.findViewById(R.id.feddback_edit_text);
        submit_bt=root.findViewById(R.id.feddback_submit_bt);
        submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback_result=feedback_text.getText().toString();

                radioGroup=root.findViewById(R.id.radio_answer_1);
                int selected_id_1=radioGroup.getCheckedRadioButtonId();
                radioButton=root.findViewById(selected_id_1);
                radioanswer.put("Food Quality",radioButton.getText().toString());

                radioGroup=root.findViewById(R.id.radio_answer_2);
                selected_id_1=radioGroup.getCheckedRadioButtonId();
                radioButton=root.findViewById(selected_id_1);
                radioanswer.put("Food Quantity",radioButton.getText().toString());

                radioGroup=root.findViewById(R.id.radio_answer_3);
                selected_id_1=radioGroup.getCheckedRadioButtonId();
                radioButton=root.findViewById(selected_id_1);
                radioanswer.put("Mess are Hygienic",radioButton.getText().toString());


                //akdsj
                radioGroup=root.findViewById(R.id.radio_answer_4);
                selected_id_1=radioGroup.getCheckedRadioButtonId();
                radioButton=root.findViewById(selected_id_1);
                radioanswer.put("Food Hygienic",radioButton.getText().toString());

                radioGroup=root.findViewById(R.id.radio_answer_5);
                selected_id_1=radioGroup.getCheckedRadioButtonId();
                radioButton=root.findViewById(selected_id_1);
                radioanswer.put("Utensils Hygienic",radioButton.getText().toString());

                radioGroup=root.findViewById(R.id.radio_answer_6);
                selected_id_1=radioGroup.getCheckedRadioButtonId();
                radioButton=root.findViewById(selected_id_1);
                radioanswer.put("Food Price",radioButton.getText().toString());

                if(feedback_result!=null){
                    Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();
                    Feedback feedback=new Feedback(feedback_result,radioanswer);
                    myRef.child("Feedback").setValue(feedback);
                }
            }
        });

        return root;
    }
}