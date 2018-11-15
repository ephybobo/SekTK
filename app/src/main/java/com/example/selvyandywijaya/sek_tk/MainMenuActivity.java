package com.example.selvyandywijaya.sek_tk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
    public void registerUser (View view)
    {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }
    public void viewRuang (View view)
    {
        Intent intent = new Intent(this, ViewRuangActivity.class);
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
    public void insertRuang (View view)
    {
        Intent intent = new Intent(this, InsertRuangActivity.class);
        startActivity(intent);
    }
    public void cctv (View view)
    {
        Intent intent = new Intent(this, cctvActivity.class);
        startActivity(intent);
    }
}
