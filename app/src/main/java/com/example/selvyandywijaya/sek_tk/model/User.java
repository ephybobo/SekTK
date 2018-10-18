package com.example.selvyandywijaya.sek_tk.model;

/**
 * Created by ASUS on 10/18/2018.
 */

public class User {
    public String nama;
    public String password;
    public String status;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String nama, String password , String status) {
        this.nama = nama;
        this.password = password;
        this.status = status;
    }
}
