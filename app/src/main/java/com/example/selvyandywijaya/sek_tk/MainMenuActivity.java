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
    public void menuJadwal (View view)
    {
        Intent intent = new Intent(this, MenuJadwalActivity.class);
        startActivity(intent);
    }
    public void menuRuang (View view)
    {
        Intent intent = new Intent(this, MenuRuangActivity.class);
        startActivity(intent);
    }

    public void cctv (View view)
    {
        Intent intent = new Intent(this, cctvActivity.class);
        startActivity(intent);
    }
}
