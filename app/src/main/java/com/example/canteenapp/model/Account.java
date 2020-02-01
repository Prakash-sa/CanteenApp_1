package com.example.canteenapp.model;

public class Account {
    private Boolean registered;
    private String messid;
    private String role;

    public Account() {
        this.setRegistered(false);
        this.setMessid("");
        this.setRole("");
    }

    public Account(Boolean status, String messid, String role) {
        registered=status;
        this.messid=messid;
        this.role=role;
        this.setRole(role);
        this.setMessid(messid);
        this.setRegistered(status);
    }

    public Boolean getRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public String getMessid() {
        return messid;
    }

    public void setMessid(String messid) {
        this.messid = messid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
