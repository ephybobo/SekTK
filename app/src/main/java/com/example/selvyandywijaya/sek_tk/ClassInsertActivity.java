package com.example.selvyandywijaya.sek_tk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ClassInsertActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_insert_class_schedule);

    }
    public void main_menu (View view)
    {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
