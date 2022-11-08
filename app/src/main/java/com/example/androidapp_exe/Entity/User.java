package com.example.androidapp_exe.Entity;

import com.google.firebase.database.IgnoreExtraProperties;


public class User {
    private String uid;
    private String email;
    private String firstName;
    private String lastName;
    private Wallet walletUid;

    public User(){

    }

    public User(String uid, String email, String firstName, String lastName, Wallet walletUid) {
        this.uid = uid;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.walletUid = walletUid;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUid() {
        return uid;
    }

    public Wallet getWalletUid() {
        return walletUid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setWalletUid(Wallet walletUid) {
        this.walletUid = walletUid;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", walletUid=" + walletUid +
                '}';
    }
}
