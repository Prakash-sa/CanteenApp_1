package com.example.canteenapp.ui.myaccount;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyAccountViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private  MutableLiveData<String> studentname;

    public MyAccountViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String>getStudentname(){return studentname;};

}