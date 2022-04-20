package com.example.hive.model;

public class HiveUser {

    private String firstname;
    private String lastname;
    private String userType;
    private String uid;
    private String contact;
    private String email;
    private String dob;

    public HiveUser(){}

    public HiveUser(String firstname, String lastname, String userType, String uid, String contact, String email, String dob) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.userType = userType;
        this.uid = uid;
        this.contact = contact;
        this.email = email;
        this.dob = dob;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
