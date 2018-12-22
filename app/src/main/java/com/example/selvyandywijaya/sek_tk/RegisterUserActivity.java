package com.example.selvyandywijaya.sek_tk;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.selvyandywijaya.sek_tk.model.Jadwal;
import com.example.selvyandywijaya.sek_tk.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RegisterUserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mUserRef = mRootRef.child("user");

    String InpNama,InNRP,InpPassword ,InpStatus = "mahasiswa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        final EditText Editnama = findViewById(R.id.innama);
        final EditText Editnrp = findViewById(R.id.innrp);
        final EditText Editpass = findViewById(R.id.inpass);

        // Spinner element
        Spinner StatusSpinner = (Spinner) findViewById(R.id.instatus);

        // Spinner click listener
        StatusSpinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> status = new ArrayList<String>();
        status.add("mahasiswa");
        status.add("dosen");
        status.add("admin");


        // Creating adapter for spinner
        ArrayAdapter<String> StatusAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, status);

        // Drop down layout style - list view with radio button
        StatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        StatusSpinner.setAdapter(StatusAdapter);


        final Button Submit = findViewById(R.id.register);
        Submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                InpNama = Editnama.getText().toString();
                InpPassword = Editpass.getText().toString();
                InNRP = Editnrp.getText().toString();

               // String newUser = mUserRef.push().getKey();

                // creating user object
                User u = new User( InpNama , InpPassword , InpStatus );

                // pushing user to 'users' node using the userId
                mUserRef.child(InNRP).setValue(u);

                Intent intent = new Intent(getApplication(), MainMenuActivity.class);
                finish();
                startActivity(intent);
            //    Toast.makeText(getApplicationContext(), "Mengirim Data" , Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        //int selected = parent.getId();

        InpStatus = item;
/*
        if(selected == R.id.kd_ruang){
            InpRuang = item;
        }else if(selected == R.id.jam){
            InpJam = item;
        }else if(selected == R.id.nama_dosen){
            InpDosen = item;
        }else if(selected == R.id.nama_matkul){
            InpMatkul = item;
        }else if(selected == R.id.hari){
            InpHari = item;
        }*/
        // Showing selected spinner item
     //   Toast.makeText(parent.getContext(), "Selected: " + item  , Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
