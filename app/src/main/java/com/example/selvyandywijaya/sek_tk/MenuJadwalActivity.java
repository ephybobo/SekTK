package com.example.selvyandywijaya.sek_tk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuJadwalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_jadwal);
    }

    public void viewJadwal (View view)
    {
        Intent intent = new Intent(this, ViewJadwalActivity.class);
        startActivity(intent);
    }

    public void updateJadwal (View view)
    {
        Intent intent = new Intent(this, UpdateJadwalActivity.class);
        startActivity(intent);
    }
    public void insertJadwal (View view)
    {
        Intent intent = new Intent(this, InsertJadwalActivity.class);
        startActivity(intent);
    }
    public void manageJadwal (View view)
    {
        Intent intent = new Intent(this, ManageJadwalActivity.class);
        startActivity(intent);
    }

}
