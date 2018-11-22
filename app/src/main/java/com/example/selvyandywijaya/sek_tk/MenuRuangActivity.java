package com.example.selvyandywijaya.sek_tk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuRuangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_ruang);
    }

    public void viewRuang (View view)
    {
        Intent intent = new Intent(this, ViewRuangActivity.class);
        startActivity(intent);
    }

    public void insertRuang (View view)
    {
        Intent intent = new Intent(this, InsertRuangActivity.class);
        startActivity(intent);
    }
}

