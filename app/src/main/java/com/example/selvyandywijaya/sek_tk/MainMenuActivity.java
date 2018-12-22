package com.example.selvyandywijaya.sek_tk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        SharedPreferences data = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Nama = data.getString("Nama", "Illegal");
        String Status = data.getString("Status", "admin");

        TextView namaText = findViewById(R.id.Nama);
        TextView statusText = findViewById(R.id.Status);

        namaText.setText(Nama);
        statusText.setText(Status);

        TableRow adm = findViewById(R.id.AdminOnly);
        TableRow admt = findViewById(R.id.AdminOnlyTxt);

        if(Status.equals("dosen") || Status.equals("mahasiswa") ){
            //Toast.makeText(this, "Mahasiswa" , Toast.LENGTH_LONG).show();
            adm.setVisibility(View.INVISIBLE);
            admt.setVisibility(View.INVISIBLE);
        }else {
            //Toast.makeText(this, "All" , Toast.LENGTH_LONG).show();
        }

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
