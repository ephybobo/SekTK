package com.example.selvyandywijaya.sek_tk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.selvyandywijaya.sek_tk.model.Jadwal;
import com.example.selvyandywijaya.sek_tk.model.Ruang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateJadwalActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRuangRef = mRootRef.child("ruang");
    DatabaseReference mJadwalRef = mRootRef.child("jadwal");

    List<String> ruangList = new ArrayList<String>();
    ArrayAdapter<String> RuangAdapter;
    Spinner RuangSpinner;
    String InpHari="Senin", InpMatkul="Mobile Programming", InpDosen="Senin", InpJam="08:00-10:50", InpRuang="A-235";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_jadwal);

        // Spinner element
        Spinner DaySpinner = (Spinner) findViewById(R.id.hari);
        Spinner MatkulSpinner = (Spinner) findViewById(R.id.nama_matkul);
        Spinner DosenSpinner = (Spinner) findViewById(R.id.nama_dosen);
        Spinner JamSpinner = (Spinner) findViewById(R.id.jam);
        RuangSpinner = (Spinner) findViewById(R.id.kd_ruang);

        // Spinner click listener
        DaySpinner.setOnItemSelectedListener(this);
        MatkulSpinner.setOnItemSelectedListener(this);
        DosenSpinner.setOnItemSelectedListener(this);
        JamSpinner.setOnItemSelectedListener(this);
        RuangSpinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> day = new ArrayList<String>();
        day.add("Senin");
        day.add("Selasa");
        day.add("Rabu");
        day.add("Kamis");
        day.add("Jumat");

        // Spinner Drop down elements
        List<String> matkul = new ArrayList<String>();
        matkul.add("Mobile Programming");
        matkul.add("Networking 2");
        matkul.add("Ubiquituous Computing");
        matkul.add("Distributed Computing");
        matkul.add("Computer Vision");

        // Spinner Drop down elements
        List<String> dosen = new ArrayList<String>();
        dosen.add("Pak Ketut");
        dosen.add("Pak Uki");
        dosen.add("Pak Arif");
        dosen.add("Pak Akok");
        dosen.add("Pak Zaini");

        // Spinner Drop down elements
        List<String> jam = new ArrayList<String>();
        jam.add("08:00-10:50");
        jam.add("11:00-13.50");
        jam.add("14:00-16.50");


        // Creating adapter for spinner
        ArrayAdapter<String> DayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, day);
        ArrayAdapter<String> MatkulAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, matkul);
        ArrayAdapter<String> DosenAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dosen);
        ArrayAdapter<String> JamAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jam);
        RuangAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ruangList);

        // Drop down layout style - list view with radio button
        DayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MatkulAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DosenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        JamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        RuangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        DaySpinner.setAdapter(DayAdapter);
        MatkulSpinner.setAdapter(MatkulAdapter);
        DosenSpinner.setAdapter(DosenAdapter);
        JamSpinner.setAdapter(JamAdapter);
        RuangSpinner.setAdapter(RuangAdapter);

        final Button Submit = findViewById(R.id.btn_update);
        Submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                String newJadwal = mJadwalRef.push().getKey();

                // creating user object
                Jadwal j = new Jadwal(InpDosen, InpHari, InpJam, InpMatkul, InpRuang);

                // pushing user to 'users' node using the userId
                mJadwalRef.child(newJadwal).setValue(j);

                Intent intent = new Intent(getApplication(), ViewRuangActivity.class);
                finish();
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Mengirim Data", Toast.LENGTH_LONG).show();

            }
        });

    }

        @Override
        protected void onStart(){
            super.onStart();

            ruangList.clear();

            mRuangRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                        //   Log.v(TAG,""+ childDataSnapshot.getKey()); //displays the key for the node
                        // Log.v(TAG,""+ childDataSnapshot.child(--ENTER THE KEY NAME eg. firstname or email etc.--).getValue());   //gives the value for given keyname
                        Ruang text = childDataSnapshot.getValue(Ruang.class);

                        Ruang r = new Ruang();
                        r.nama = text.nama ;
                        // adding movie to movies array
                        ruangList.add(r.nama);

                    }

                    RuangAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // On selecting a spinner item
            String item = parent.getItemAtPosition(position).toString();
            int selected = parent.getId();

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
            }
            // Showing selected spinner item
            Toast.makeText(parent.getContext(), "Selected: " + item  , Toast.LENGTH_LONG).show();
        }
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

        public void main_menu (View view)
        {
            Intent intent = new Intent(this, MainMenuActivity.class);
            startActivity(intent);
        }




}
