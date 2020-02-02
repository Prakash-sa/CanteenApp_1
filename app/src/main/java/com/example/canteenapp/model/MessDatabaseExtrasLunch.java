package com.example.canteenapp.model;

import android.graphics.Path;

public class MessDatabaseExtrasLunch {

    private String GheeType;
    private String SweetType;
    private String JuiceType;
    private String IceCreamType;
    private String Optional1;
    private String Optional2;
    private String Optional3;
    private String Optional4;
    private String Optional5;
    private String Optional6;

    public MessDatabaseExtrasLunch(){

    }
    public MessDatabaseExtrasLunch(String GheeType, String SweetType, String JuiceType, String IceCreamType, String Optional1, String Optional2, String Optional3, String Optional4, String Optional5, String Optional6){
        this.GheeType=GheeType;
        this.SweetType=SweetType;
        this.JuiceType=JuiceType;
        this.IceCreamType=IceCreamType;
        this.Optional1= Optional1;
        this.Optional2=Optional2;
        this.Optional3=Optional3;
        this.Optional4=Optional4;
        this.Optional5=Optional5;
        this.Optional6=Optional6;


    }

    public String getGheeType() {
        return GheeType;
    }

    public String getSweetType() {
        return SweetType;
    }

    public String getJuiceType() {
        return JuiceType;
    }

    public String getIceCreamType() {
        return IceCreamType;
    }

    public String getOptional1() {
        return Optional1;
    }

    public String getOptional2() {
        return Optional2;
    }

    public String getOptional3() {
        return Optional3;
    }

    public String getOptional4() {
        return Optional4;
    }

    public String getOptional5() {
        return Optional5;
    }

    public String getOptional6() {
        return Optional6;
    }

    public void setGheeType(String gheeType) {
        GheeType = gheeType;
    }

    public void setSweetType(String sweetType) {
        SweetType = sweetType;
    }

    public void setJuiceType(String juiceType) {
        JuiceType = juiceType;
    }

    public void setIceCreamType(String iceCreamType) {
        IceCreamType = iceCreamType;
    }

    public void setOptional1(String optional1) {
        Optional1 = optional1;
    }

    public void setOptional2(String optional2) {
        Optional2 = optional2;
    }

    public void setOptional3(String optional3) {
        Optional3 = optional3;
    }

    public void setOptional4(String optional4) {
        Optional4 = optional4;
    }

    public void setOptional5(String optional5) {
        Optional5 = optional5;
    }

    public void setOptional6(String optional6) {
        Optional6 = optional6;
    }
}
