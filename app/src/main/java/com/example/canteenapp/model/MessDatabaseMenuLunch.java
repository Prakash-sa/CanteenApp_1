package com.example.canteenapp.model;

public class MessDatabaseMenuLunch {

    private String ChapatiType;
    private String RiceType;
    private String SaladType;
    private String DallType;
    private String SabjiType;
    private String CurdType;
    private String Optional1;
    private String Optional2;
    private String Optional3;
    private String Optional4;

    public MessDatabaseMenuLunch(){

    }

    public MessDatabaseMenuLunch(String ChapatiType, String RiceType, String SaladType, String DallType, String SabjiType, String CurdType, String Optional1, String Optional2, String Optional3, String Optional4){
        this.ChapatiType=ChapatiType;
        this.RiceType=RiceType;
        this.SabjiType=SabjiType;
        this.SaladType=SaladType;
        this.DallType=DallType;
        this.CurdType=CurdType;
        this.Optional1=Optional1;
        this.Optional2=Optional2;
        this.Optional3=Optional3;
        this.Optional4=Optional4;
    }

    public String getChapatiType() {
        return ChapatiType;
    }

    public String getRiceType() {
        return RiceType;
    }

    public String getSaladType() {
        return SaladType;
    }

    public String getDallType() {
        return DallType;
    }

    public String getSabjiType() {
        return SabjiType;
    }

    public String getCurdType() {
        return CurdType;
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

    public void setChapatiType(String chapatiType) {
        ChapatiType = chapatiType;
    }

    public void setRiceType(String riceType) {
        RiceType = riceType;
    }

    public void setSaladType(String saladType) {
        SaladType = saladType;
    }

    public void setDallType(String dallType) {
        DallType = dallType;
    }

    public void setSabjiType(String sabjiType) {
        SabjiType = sabjiType;
    }

    public void setCurdType(String curdType) {
        CurdType = curdType;
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
}
