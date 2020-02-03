package com.example.canteenapp.ui.student.payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.canteenapp.R;
import com.example.canteenapp.model.MessDatabaseExtrasBreakfast;
import com.example.canteenapp.model.MessDatabaseExtrasDinner;
import com.example.canteenapp.model.MessDatabaseExtrasLunch;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.List;

import static com.example.canteenapp.ui.student.home.StudentHomeFragment.getCurrentDay;

public class PaymentAcitivity extends AppCompatActivity {


    private MessDatabaseExtrasBreakfast messDatabaseExtrasBreakfast;
    private MessDatabaseExtrasLunch messDatabaseExtrasLunch;
    private MessDatabaseExtrasDinner messDatabaseExtrasDinner;
    public static FirebaseUser user;

    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7,checkBox8,checkBox9,checkBox10;
    private CheckBox checkBoxlunch1,checkBoxlunch2,checkBoxlunch3,checkBoxlunch4,checkBoxlunch5,checkBoxlunch6,checkBoxlunch7,checkBoxlunch8,checkBoxlunch9,checkBoxlunch10;
    private CheckBox checkBoxdinner1,checkBoxdinner2,checkBoxdinner3,checkBoxdinner4,checkBoxdinner5,checkBoxdinner6,checkBoxdinner7,checkBoxdinner8,checkBoxdinner9,checkBoxdinner10;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Mess");
    private TwoWayView twoWayViewbreakfast,twoWayViewlunch,twoWayViewdinner;
    private List<String> items1=new ArrayList<>();
    private List<String>items2=new ArrayList<>();
    private List<String>items3=new ArrayList<>();
    final int UPI_PAYMENT = 0;
    private TextView extraAmount;
    private String today;
    private Button pay_bt,add_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_acitivity);
        pay_bt=findViewById(R.id.payment_bt);
        extraAmount = findViewById(R.id.extra_cost);
        add_bt=findViewById(R.id.add_bt);
        user = FirebaseAuth.getInstance().getCurrentUser();

        today=getCurrentDay();
        twoWayViewbreakfast=findViewById(R.id.extra_breakfast_listview);
        twoWayViewlunch=findViewById(R.id.extra_lunch_listview);
        twoWayViewdinner=findViewById(R.id.extra_dinner_listview);

        checkBox1=findViewById(R.id.item1_breakfast);
        checkBox2=findViewById(R.id.item2_breakfast);
        checkBox3=findViewById(R.id.item3_breakfast);
        checkBox4=findViewById(R.id.item4_breakfast);
        checkBox5=findViewById(R.id.item5_breakfast);
        checkBox6=findViewById(R.id.item6_breakfast);
        checkBox7=findViewById(R.id.item7_breakfast);
        checkBox8=findViewById(R.id.item8_breakfast);
        checkBox9=findViewById(R.id.item9_breakfast);
        checkBox10=findViewById(R.id.item10_breakfast);

        checkBoxlunch1=findViewById(R.id.item1_lunch);
        checkBoxlunch2=findViewById(R.id.item2_lunch);
        checkBoxlunch3=findViewById(R.id.item3_lunch);
        checkBoxlunch4=findViewById(R.id.item4_lunch);
        checkBoxlunch5=findViewById(R.id.item5_lunch);
        checkBoxlunch6=findViewById(R.id.item6_lunch);
        checkBoxlunch7=findViewById(R.id.item7_lunch);
        checkBoxlunch8=findViewById(R.id.item8_lunch);
        checkBoxlunch9=findViewById(R.id.item9_lunch);
        checkBoxlunch10=findViewById(R.id.item10_lunch);

        checkBoxdinner1=findViewById(R.id.item1_dinner);
        checkBoxdinner2=findViewById(R.id.item2_dinner);
        checkBoxdinner3=findViewById(R.id.item3_dinner);
        checkBoxdinner4=findViewById(R.id.item4_dinner);
        checkBoxdinner5=findViewById(R.id.item5_dinner);
        checkBoxdinner6=findViewById(R.id.item6_dinner);
        checkBoxdinner7=findViewById(R.id.item7_dinner);
        checkBoxdinner8=findViewById(R.id.item8_dinner);
        checkBoxdinner9=findViewById(R.id.item9_dinner);
        checkBoxdinner10=findViewById(R.id.item10_dinner);


        firebasecheck();




        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total=0;
                if(checkBox1.isChecked())total+=Integer.parseInt(checkBox1.getText().toString());
                if(checkBox2.isChecked())total+=Integer.parseInt(checkBox2.getText().toString());
                if(checkBox3.isChecked())total+=Integer.parseInt(checkBox3.getText().toString());
                if(checkBox4.isChecked())total+=Integer.parseInt(checkBox4.getText().toString());
                if(checkBox5.isChecked())total+=Integer.parseInt(checkBox5.getText().toString());
                if(checkBox6.isChecked())total+=Integer.parseInt(checkBox6.getText().toString());
                if(checkBox7.isChecked())total+=Integer.parseInt(checkBox7.getText().toString());
                if(checkBox8.isChecked())total+=Integer.parseInt(checkBox8.getText().toString());
                if(checkBox9.isChecked())total+=Integer.parseInt(checkBox9.getText().toString());
                if(checkBox10.isChecked())total+=Integer.parseInt(checkBox10.getText().toString());

                if(checkBoxlunch1.isChecked())total+=Integer.parseInt(checkBoxlunch1.getText().toString());
                if(checkBoxlunch2.isChecked())total+=Integer.parseInt(checkBoxlunch2.getText().toString());
                if(checkBoxlunch3.isChecked())total+=Integer.parseInt(checkBoxlunch3.getText().toString());
                if(checkBoxlunch4.isChecked())total+=Integer.parseInt(checkBoxlunch4.getText().toString());
                if(checkBoxlunch5.isChecked())total+=Integer.parseInt(checkBoxlunch5.getText().toString());
                if(checkBoxlunch6.isChecked())total+=Integer.parseInt(checkBoxlunch6.getText().toString());
                if(checkBoxlunch7.isChecked())total+=Integer.parseInt(checkBoxlunch7.getText().toString());
                if(checkBoxlunch8.isChecked())total+=Integer.parseInt(checkBoxlunch8.getText().toString());
                if(checkBoxlunch9.isChecked())total+=Integer.parseInt(checkBoxlunch9.getText().toString());
                if(checkBoxlunch10.isChecked())total+=Integer.parseInt(checkBoxlunch10.getText().toString());

                if(checkBoxdinner1.isChecked())total+=Integer.parseInt(checkBoxdinner1.getText().toString());
                if(checkBoxdinner2.isChecked())total+=Integer.parseInt(checkBoxdinner2.getText().toString());
                if(checkBoxdinner3.isChecked())total+=Integer.parseInt(checkBoxdinner3.getText().toString());
                if(checkBoxdinner4.isChecked())total+=Integer.parseInt(checkBoxdinner4.getText().toString());
                if(checkBoxdinner5.isChecked())total+=Integer.parseInt(checkBoxdinner5.getText().toString());
                if(checkBoxdinner6.isChecked())total+=Integer.parseInt(checkBoxdinner6.getText().toString());
                if(checkBoxdinner7.isChecked())total+=Integer.parseInt(checkBoxdinner7.getText().toString());
                if(checkBoxdinner8.isChecked())total+=Integer.parseInt(checkBoxdinner8.getText().toString());
                if(checkBoxdinner9.isChecked())total+=Integer.parseInt(checkBoxdinner9.getText().toString());
                if(checkBoxdinner10.isChecked())total+=Integer.parseInt(checkBoxdinner10.getText().toString());
                extraAmount.setText(total+"");
            }
        });



        pay_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Integer.parseInt(extraAmount.getText().toString());

                if (amount <= 0) {
                    Toast.makeText(PaymentAcitivity.this, "Invalid selection.", Toast.LENGTH_LONG).show();
                    return;
                }

                payUsingUpi("Prakash Saini","sainiprakash525@okhdfcbank","Requesting money for extras in mess menu","" + amount);
            }
        });



    }

    private void firebasecheck(){

        myRef.child("extra").child(today).child("Breakfast").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messDatabaseExtrasBreakfast=new MessDatabaseExtrasBreakfast();
                messDatabaseExtrasBreakfast=dataSnapshot.getValue(MessDatabaseExtrasBreakfast.class);
                if(messDatabaseExtrasBreakfast!=null)setCheckboxBreakfast();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef.child("extra").child(today).child("Lunch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messDatabaseExtrasLunch=new MessDatabaseExtrasLunch();
                messDatabaseExtrasLunch=dataSnapshot.getValue(MessDatabaseExtrasLunch.class);
                if(messDatabaseExtrasLunch!=null)setCheckboxLunch();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef.child("extra").child(today).child("Dinner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messDatabaseExtrasDinner=new MessDatabaseExtrasDinner();
                messDatabaseExtrasDinner=dataSnapshot.getValue(MessDatabaseExtrasDinner.class);
                if(messDatabaseExtrasDinner!=null)setCheckboxDinner();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setCheckboxBreakfast(){
        if(!messDatabaseExtrasBreakfast.getPrice1().isEmpty())checkBox1.setText(messDatabaseExtrasBreakfast.getPrice1());
        else checkBox1.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice2().isEmpty())checkBox2.setText(messDatabaseExtrasBreakfast.getPrice2());
        else checkBox2.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice3().isEmpty())checkBox3.setText(messDatabaseExtrasBreakfast.getPrice3());
        else checkBox3.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice4().isEmpty())checkBox4.setText(messDatabaseExtrasBreakfast.getPrice4());
        else checkBox4.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice5().isEmpty())checkBox5.setText(messDatabaseExtrasBreakfast.getPrice5());
        else checkBox5.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice6().isEmpty())checkBox6.setText(messDatabaseExtrasBreakfast.getPrice6());
        else checkBox6.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice7().isEmpty())checkBox7.setText(messDatabaseExtrasBreakfast.getPrice7());
        else checkBox7.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice8().isEmpty())checkBox8.setText(messDatabaseExtrasBreakfast.getPrice8());
        else checkBox8.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice9().isEmpty())checkBox9.setText(messDatabaseExtrasBreakfast.getPrice9());
        else checkBox9.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice10().isEmpty())checkBox10.setText(messDatabaseExtrasBreakfast.getPrice9());
        else checkBox10.setVisibility(View.INVISIBLE);
    }
    private void setCheckboxLunch(){

        if(!messDatabaseExtrasLunch.getPrice1().isEmpty())checkBoxlunch1.setText(messDatabaseExtrasLunch.getPrice1());
        else checkBoxlunch1.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice2().isEmpty())checkBoxlunch2.setText(messDatabaseExtrasLunch.getPrice2());
        else checkBoxlunch2.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice3().isEmpty())checkBoxlunch3.setText(messDatabaseExtrasLunch.getPrice3());
        else checkBoxlunch3.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice4().isEmpty())checkBoxlunch4.setText(messDatabaseExtrasLunch.getPrice4());
        else checkBoxlunch4.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice5().isEmpty())checkBoxlunch5.setText(messDatabaseExtrasLunch.getPrice5());
        else checkBoxlunch5.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice6().isEmpty())checkBoxlunch6.setText(messDatabaseExtrasLunch.getPrice6());
        else checkBoxlunch6.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice7().isEmpty())checkBoxlunch7.setText(messDatabaseExtrasLunch.getPrice7());
        else checkBoxlunch7.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice8().isEmpty())checkBoxlunch8.setText(messDatabaseExtrasLunch.getPrice8());
        else checkBoxlunch8.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice9().isEmpty())checkBoxlunch9.setText(messDatabaseExtrasLunch.getPrice9());
        else checkBoxlunch9.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice10().isEmpty())checkBoxlunch10.setText(messDatabaseExtrasLunch.getPrice9());
        else checkBoxlunch10.setVisibility(View.INVISIBLE);
    }
    private void setCheckboxDinner(){
        if(!messDatabaseExtrasDinner.getPrice1().isEmpty())checkBoxdinner1.setText(messDatabaseExtrasDinner.getPrice1());
        else checkBoxdinner1.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice2().isEmpty())checkBoxdinner2.setText(messDatabaseExtrasDinner.getPrice2());
        else checkBoxdinner2.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice3().isEmpty())checkBoxdinner3.setText(messDatabaseExtrasDinner.getPrice3());
        else checkBoxdinner3.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice4().isEmpty())checkBoxdinner4.setText(messDatabaseExtrasDinner.getPrice4());
        else checkBoxdinner4.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice5().isEmpty())checkBoxdinner5.setText(messDatabaseExtrasDinner.getPrice5());
        else checkBoxdinner5.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice6().isEmpty())checkBoxdinner6.setText(messDatabaseExtrasDinner.getPrice6());
        else checkBoxdinner6.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice7().isEmpty())checkBoxdinner7.setText(messDatabaseExtrasDinner.getPrice7());
        else checkBoxdinner7.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice8().isEmpty())checkBoxdinner8.setText(messDatabaseExtrasDinner.getPrice8());
        else checkBoxdinner8.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice9().isEmpty())checkBoxdinner9.setText(messDatabaseExtrasDinner.getPrice9());
        else checkBoxdinner9.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice10().isEmpty())checkBoxdinner10.setText(messDatabaseExtrasDinner.getPrice9());
        else checkBoxdinner10.setVisibility(View.INVISIBLE);
    }
    /*

    private void setCheckboxBreakfast(){
     if(!messDatabaseExtrasBreakfast.getPrice1().isEmpty())checkBox1.setText(messDatabaseExtrasBreakfast.getGheeType()+":"+messDatabaseExtrasBreakfast.getPrice1());
     else checkBox1.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice2().isEmpty())checkBox2.setText(messDatabaseExtrasBreakfast.getSweetType()+":"+messDatabaseExtrasBreakfast.getPrice2());
        else checkBox2.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice3().isEmpty())checkBox3.setText(messDatabaseExtrasBreakfast.getJuiceType()+":"+messDatabaseExtrasBreakfast.getPrice3());
        else checkBox3.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice4().isEmpty())checkBox4.setText(messDatabaseExtrasBreakfast.getIceCreamType()+":"+messDatabaseExtrasBreakfast.getPrice4());
        else checkBox4.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice5().isEmpty())checkBox5.setText(messDatabaseExtrasBreakfast.getOptional1()+":"+messDatabaseExtrasBreakfast.getPrice5());
        else checkBox5.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice6().isEmpty())checkBox6.setText(messDatabaseExtrasBreakfast.getOptional2()+":"+messDatabaseExtrasBreakfast.getPrice6());
        else checkBox6.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice7().isEmpty())checkBox7.setText(messDatabaseExtrasBreakfast.getOptional3()+":"+messDatabaseExtrasBreakfast.getPrice7());
        else checkBox7.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice8().isEmpty())checkBox8.setText(messDatabaseExtrasBreakfast.getOptional4()+":"+messDatabaseExtrasBreakfast.getPrice8());
        else checkBox8.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice9().isEmpty())checkBox9.setText(messDatabaseExtrasBreakfast.getOptional5()+":"+messDatabaseExtrasBreakfast.getPrice9());
        else checkBox9.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasBreakfast.getPrice10().isEmpty())checkBox10.setText(messDatabaseExtrasBreakfast.getOptional6()+":"+messDatabaseExtrasBreakfast.getPrice9());
        else checkBox10.setVisibility(View.INVISIBLE);
    }
    private void setCheckboxLunch(){

        if(!messDatabaseExtrasLunch.getPrice1().isEmpty())checkBoxlunch1.setText(messDatabaseExtrasLunch.getGheeType()+":"+messDatabaseExtrasLunch.getPrice1());
        else checkBoxlunch1.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice2().isEmpty())checkBoxlunch2.setText(messDatabaseExtrasLunch.getSweetType()+":"+messDatabaseExtrasLunch.getPrice2());
        else checkBoxlunch2.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice3().isEmpty())checkBoxlunch3.setText(messDatabaseExtrasLunch.getJuiceType()+":"+messDatabaseExtrasLunch.getPrice3());
        else checkBoxlunch3.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice4().isEmpty())checkBoxlunch4.setText(messDatabaseExtrasLunch.getIceCreamType()+":"+messDatabaseExtrasLunch.getPrice4());
        else checkBoxlunch4.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice5().isEmpty())checkBoxlunch5.setText(messDatabaseExtrasLunch.getOptional1()+":"+messDatabaseExtrasLunch.getPrice5());
        else checkBoxlunch5.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice6().isEmpty())checkBoxlunch6.setText(messDatabaseExtrasLunch.getOptional2()+":"+messDatabaseExtrasLunch.getPrice6());
        else checkBoxlunch6.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice7().isEmpty())checkBoxlunch7.setText(messDatabaseExtrasLunch.getOptional3()+":"+messDatabaseExtrasLunch.getPrice7());
        else checkBoxlunch7.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice8().isEmpty())checkBoxlunch8.setText(messDatabaseExtrasLunch.getOptional4()+":"+messDatabaseExtrasLunch.getPrice8());
        else checkBoxlunch8.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice9().isEmpty())checkBoxlunch9.setText(messDatabaseExtrasLunch.getOptional5()+":"+messDatabaseExtrasLunch.getPrice9());
        else checkBoxlunch9.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasLunch.getPrice10().isEmpty())checkBoxlunch10.setText(messDatabaseExtrasLunch.getOptional6()+":"+messDatabaseExtrasLunch.getPrice9());
        else checkBoxlunch10.setVisibility(View.INVISIBLE);
    }
    private void setCheckboxDinner(){
        if(!messDatabaseExtrasDinner.getPrice1().isEmpty())checkBoxdinner1.setText(messDatabaseExtrasDinner.getGheeType()+":"+messDatabaseExtrasDinner.getPrice1());
        else checkBoxdinner1.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice2().isEmpty())checkBoxdinner2.setText(messDatabaseExtrasDinner.getSweetType()+":"+messDatabaseExtrasDinner.getPrice2());
        else checkBoxdinner2.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice3().isEmpty())checkBoxdinner3.setText(messDatabaseExtrasDinner.getJuiceType()+":"+messDatabaseExtrasDinner.getPrice3());
        else checkBoxdinner3.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice4().isEmpty())checkBoxdinner4.setText(messDatabaseExtrasDinner.getIceCreamType()+":"+messDatabaseExtrasDinner.getPrice4());
        else checkBoxdinner4.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice5().isEmpty())checkBoxdinner5.setText(messDatabaseExtrasDinner.getOptional1()+":"+messDatabaseExtrasDinner.getPrice5());
        else checkBoxdinner5.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice6().isEmpty())checkBoxdinner6.setText(messDatabaseExtrasDinner.getOptional2()+":"+messDatabaseExtrasDinner.getPrice6());
        else checkBoxdinner6.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice7().isEmpty())checkBoxdinner7.setText(messDatabaseExtrasDinner.getOptional3()+":"+messDatabaseExtrasDinner.getPrice7());
        else checkBoxdinner7.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice8().isEmpty())checkBoxdinner8.setText(messDatabaseExtrasDinner.getOptional4()+":"+messDatabaseExtrasDinner.getPrice8());
        else checkBoxdinner8.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice9().isEmpty())checkBoxdinner9.setText(messDatabaseExtrasDinner.getOptional5()+":"+messDatabaseExtrasDinner.getPrice9());
        else checkBoxdinner9.setVisibility(View.INVISIBLE);

        if(!messDatabaseExtrasDinner.getPrice10().isEmpty())checkBoxdinner10.setText(messDatabaseExtrasDinner.getOptional6()+":"+messDatabaseExtrasDinner.getPrice9());
        else checkBoxdinner10.setVisibility(View.INVISIBLE);
    }
*/
    void payUsingUpi(  String name,String upiId, String note, String amount) {
        Log.e("main ", "name "+name +"--up--"+upiId+"--"+ note+"--"+amount);
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                //.appendQueryParameter("mc", "")
                //.appendQueryParameter("tid", "02125412")
                //.appendQueryParameter("tr", "25584584")
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                //.appendQueryParameter("refUrl", "blueapp")
                .build();
        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);
        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");
        // check if intent resolves
        if(null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(PaymentAcitivity.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("main ", "response "+resultCode );
        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.e("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.e("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    //when user simply back without payment
                    Log.e("UPI", "onActivityResult: " + "Return data is null");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }
    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(PaymentAcitivity.this)) {
            String str = data.get(0);
            Log.e("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }
            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(PaymentAcitivity.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "payment successfull: "+approvalRefNo);
                myRef.child("payment").child(user.getPhoneNumber()).setValue(extraAmount.getText().toString());
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(PaymentAcitivity.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "Cancelled by user: "+approvalRefNo);
            }
            else {
                Toast.makeText(PaymentAcitivity.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "failed payment: "+approvalRefNo);
            }
        } else {
            Log.e("UPI", "Internet issue: ");
            Toast.makeText(PaymentAcitivity.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }
}
