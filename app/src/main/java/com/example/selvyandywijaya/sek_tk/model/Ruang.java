package com.example.selvyandywijaya.sek_tk.model;

import android.net.Uri;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ASUS on 11/1/2018.
 */

public class Ruang {
    public String key;

    public String nama;
    public double lat;
    public double lng;
    public String ImgUri;
    public ArrayList<String> image;
    public int cap;
    public ArrayList<String> shift;



    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Ruang() {
    }
    //insert
    public Ruang(String nama , String imguri ,double lat , double lng ,ArrayList<String> img , int cap ,ArrayList<String> shift) {
            this.nama = nama; this.ImgUri = imguri;this.lat = lat; this.lng = lng;this.image = img;this.cap=cap;this.shift=shift;
    }
    //view
    public Ruang(String key , String nama , String imguri , int cap ,ArrayList<String> shift) {
        this.key = key; this.nama = nama; this.ImgUri = imguri;this.cap=cap; this.shift=shift;
    }

    public Ruang(String key,String nama) {
        this.key = key;
        this.nama = nama;
    }
}
