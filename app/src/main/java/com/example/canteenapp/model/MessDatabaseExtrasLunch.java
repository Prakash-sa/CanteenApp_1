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
    private String init;
    private String price2;
    private String price3;
    private String price4;
    private String price5;
    private String price6;
    private String price7;
    private String price8;
    private String price9;
    private String price10;

    public MessDatabaseExtrasLunch(){

    }
    public MessDatabaseExtrasLunch(String GheeType, String SweetType, String JuiceType, String IceCreamType, String Optional1, String Optional2, String Optional3, String Optional4, String Optional5, String Optional6,
                                       String price1,String price2,String price3,String price4,String price5,String price6,
                                       String price7,String price8,String price9,String price10){
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
        this.init=price1;
        this.price2=price2;
        this.price3=price3;
        this.price4=price4;

        this.price5=price5;
        this.price6=price6;
        this.price7=price7;
        this.price8=price8;
        this.price9=price9;
        this.price10=price10;
    }

    public String getPrice1() {
        return init;
    }

    public void setPrice1(String price1) {
        this.init = price1;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public String getPrice3() {
        return price3;
    }

    public void setPrice3(String price3) {
        this.price3 = price3;
    }

    public String getPrice4() {
        return price4;
    }

    public void setPrice4(String price4) {
        this.price4 = price4;
    }

    public String getPrice5() {
        return price5;
    }

    public void setPrice5(String price5) {
        this.price5 = price5;
    }

    public String getPrice6() {
        return price6;
    }

    public void setPrice6(String price6) {
        this.price6 = price6;
    }

    public String getPrice7() {
        return price7;
    }

    public void setPrice7(String price7) {
        this.price7 = price7;
    }

    public String getPrice8() {
        return price8;
    }

    public void setPrice8(String price8) {
        this.price8 = price8;
    }

    public String getPrice9() {
        return price9;
    }

    public void setPrice9(String price9) {
        this.price9 = price9;
    }

    public String getPrice10() {
        return price10;
    }

    public void setPrice10(String price10) {
        this.price10 = price10;
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
