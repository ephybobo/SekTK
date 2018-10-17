package com.example.selvyandywijaya.sek_tk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
    public void register (View view)
    {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
    public void update (View view)
    {
        Intent intent = new Intent(this, ClassUpdatedActivity.class);
        startActivity(intent);
    }
    public void insert (View view)
    {
        Intent intent = new Intent(this, ClassInsertActivity.class);
        startActivity(intent);
    }
}
