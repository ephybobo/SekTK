package com.example.selvyandywijaya.sek_tk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MenuRuangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_ruang);

        SharedPreferences data = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Status = data.getString("Status", "admin");

        LinearLayout insertArea = findViewById(R.id.Insert);
        LinearLayout manageArea = findViewById(R.id.Manage);


        if(Status.equals("dosen") || Status.equals("mahasiswa") ){
            // Toast.makeText(this, "Mahasiswa" , Toast.LENGTH_LONG).show();
            insertArea.setVisibility(View.INVISIBLE);
        }

        if(Status.equals("mahasiswa") ){
            // Toast.makeText(this, "Mahasiswa" , Toast.LENGTH_LONG).show();
            manageArea.setVisibility(View.INVISIBLE);
        }

    }

    public void viewRuang (View view)
    {
        Intent intent = new Intent(this, ViewRuangActivity.class);
        startActivity(intent);
    }

    /*
    public void updateRuang (View view)
    {
        Intent intent = new Intent(this, UpdateRuangActivity.class);
        startActivity(intent);
    }*/

    public void insertRuang (View view)
    {
        Intent intent = new Intent(this, InsertRuangActivity.class);
        startActivity(intent);
    }


    public void manageRuang (View view)
    {
        Intent intent = new Intent(this, ManageRuangActivity.class);
        startActivity(intent);
    }
}

