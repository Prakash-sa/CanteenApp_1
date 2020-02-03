package com.example.canteenapp.model;

public class MealTimeDatabase {
    private String startbreakfast,startlunch,startdinner,endbreakfast,endlunch,enddinner;

    public MealTimeDatabase(){

    }

    public MealTimeDatabase(String startbreakfast,String startlunch,String startdinner,String endbreakfast,String endlunch,String enddinner){
        this.startbreakfast=startbreakfast;
        this.startlunch=startlunch;
        this.startdinner=startdinner;
        this.endbreakfast=endbreakfast;
        this.endlunch=endlunch;
        this.enddinner=enddinner;
    }

    public String getStartbreakfast() {
        return startbreakfast;
    }

    public void setStartbreakfast(String startbreakfast) {
        this.startbreakfast = startbreakfast;
    }

    public String getStartlunch() {
        return startlunch;
    }

    public void setStartlunch(String startlunch) {
        this.startlunch = startlunch;
    }

    public String getStartdinner() {
        return startdinner;
    }

    public void setStartdinner(String startdinner) {
        this.startdinner = startdinner;
    }

    public String getEndbreakfast() {
        return endbreakfast;
    }

    public void setEndbreakfast(String endbreakfast) {
        this.endbreakfast = endbreakfast;
    }

    public String getEndlunch() {
        return endlunch;
    }

    public void setEndlunch(String endlunch) {
        this.endlunch = endlunch;
    }

    public String getEnddinner() {
        return enddinner;
    }

    public void setEnddinner(String enddinner) {
        this.enddinner = enddinner;
    }
}
