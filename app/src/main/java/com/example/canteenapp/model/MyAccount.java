package com.example.canteenapp.model;

public class MyAccount {

    public MyAccount(){

    }

    private String url,name,email,mess_id,rebate,refund,monthly_payment,extras_payment;

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMess_id() {
        return mess_id;
    }

    public String getRebate() {
        return rebate;
    }

    public String getRefund() {
        return refund;
    }

    public String getMonthly_payment() {
        return monthly_payment;
    }

    public String getExtras_payment() {
        return extras_payment;
    }

    public MyAccount(String url, String name, String email, String mess_id, String rebate, String refund, String monthly_payment, String extras_payment){
        this.url=url;
        this.name=name;
        this.email=email;
        this.mess_id=mess_id;
        this.rebate=rebate;
        this.refund=refund;
        this.monthly_payment=monthly_payment;
        this.extras_payment=extras_payment;
    }
}
