package com.example.canteenapp.model;

import java.util.HashMap;

public class Feedback {

    private String feedback_text;
    private HashMap<String,String> radio_answer=new HashMap<String,String>();

    public Feedback() {
    }

    public HashMap<String, String> getRadio_answer() {
        return radio_answer;
    }

    public Feedback(String feedback_text, HashMap<String,String> radio_answer){
        this.feedback_text=feedback_text;
        this.radio_answer=radio_answer;
    }

    public String getFeedback_text() {
        return feedback_text;
    }

}
