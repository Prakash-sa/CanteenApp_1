package com.example.canteenapp.ui.applyleave;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ApplyLeaveViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ApplyLeaveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}