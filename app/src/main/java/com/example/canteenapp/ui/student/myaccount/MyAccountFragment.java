package com.example.canteenapp.ui.student.myaccount;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.canteenapp.Adapter.MyAccount;
import com.example.canteenapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAccountFragment extends Fragment {


    private MyAccountViewModel myAccountViewModel;
    private TextView student_name,student_email,student_medd_id,student_rebate,student_refund,student_monthly_payment,student_extras_payment;
    private ImageView profile_picture;

    private Context mcontext;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Students");
    private ArrayList<String> student_details=new ArrayList<>();

    private MyAccount myaccount;

    @Override
    public void onStart() {
        super.onStart();
        
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mcontext=context;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        myAccountViewModel = ViewModelProviders.of(this).get(MyAccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_account, container, false);

        student_name=root.findViewById(R.id.student_name);
        student_email=root.findViewById(R.id.student_email);
        student_medd_id=root.findViewById(R.id.student_mees_id);
        student_rebate=root.findViewById(R.id.student_rebate);
        student_refund=root.findViewById(R.id.student_refund);
        student_monthly_payment=root.findViewById(R.id.student_monthly_payment);
        student_extras_payment=root.findViewById(R.id.student_extras_payment);

        profile_picture=root.findViewById(R.id.student_profile_picture);
        student_name=root.findViewById(R.id.student_name);
        myRef.child("Details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myaccount=dataSnapshot.getValue(MyAccount.class);
                Log.i("MyAccount",myaccount.getEmail());
                student_details.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    student_details.add(data.getKey());
                }
                Log.i("Length",student_details.size()+"");
                UpdateUi();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i(String.valueOf(mcontext),"MyAccountDetails Cancelled");
            }
        });
        return root;
    }

    private void UpdateUi(){
        student_name.setText(myaccount.getName());
        student_email.setText(myaccount.getEmail());
        student_medd_id.setText(myaccount.getMess_id());
        student_rebate.setText(myaccount.getRebate()+"Rs");
        student_refund.setText(myaccount.getRefund()+"Rs");
        student_monthly_payment.setText(myaccount.getMonthly_payment()+"Rs");
        student_extras_payment.setText(myaccount.getExtras_payment()+"Rs");
        Glide.with(this).load(myaccount.getUrl()).into(profile_picture);
    }
}