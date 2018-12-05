package com.example.selvyandywijaya.sek_tk.model;

/**
 * Created by ASUS on 11/1/2018.
 */

public class Jadwal {
    public String key;

    public String dosen;
    public String hari;
    public String jam;
    public String matakuliah;
    public String ruang;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Jadwal() {
    }

    public Jadwal(String dosen, String hari , String jam ,String matakuliah , String ruang  ) {
        this.dosen = dosen;
        this.hari = hari;
        this.jam = jam;
        this.matakuliah = matakuliah;
        this.ruang = ruang;
    }

    public Jadwal(String key,String dosen, String hari , String jam ,String matakuliah , String ruang  ) {
        this.key = key;

        this.dosen = dosen;
        this.hari = hari;
        this.jam = jam;
        this.matakuliah = matakuliah;
        this.ruang = ruang;
    }
}
