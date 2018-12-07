package com.example.selvyandywijaya.sek_tk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MenuJadwalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_jadwal);

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

    public void viewJadwal (View view)
    {
        Intent intent = new Intent(this, ViewAllJadwalActivity.class);
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
