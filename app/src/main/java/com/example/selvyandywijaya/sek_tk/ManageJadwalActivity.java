package com.example.selvyandywijaya.sek_tk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selvyandywijaya.sek_tk.adapter.JadwalAdapter;
import com.example.selvyandywijaya.sek_tk.adapter.ManageJadwalAdapter;
import com.example.selvyandywijaya.sek_tk.model.Jadwal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManageJadwalActivity extends AppCompatActivity {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mJadwalRef = mRootRef.child("jadwal");

    private List<Jadwal> jadwalList = new ArrayList<Jadwal>();
    private ListView listView;
    private ManageJadwalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_jadwal);

        listView = (ListView) findViewById(R.id.lv);
        adapter = new ManageJadwalAdapter(this, jadwalList);
        listView.setAdapter(adapter);

     //   Toast.makeText(this, "create" , Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStart(){
        super.onStart();

       // Toast.makeText(this, "start" , Toast.LENGTH_LONG).show();

        // Get the Intent that started this activity and extract the string
       // Intent intent = getIntent();
       // String room = intent.getStringExtra("RuangName");

       // TextView txtName = findViewById(R.id.NamaRuang);
       // txtName.setText(room);
    //.orderByChild("ruang").equalTo(room)
        mJadwalRef.orderByChild("ruang").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                jadwalList.clear();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    //   Log.v(TAG,""+ childDataSnapshot.getKey()); //displays the key for the node
                    // Log.v(TAG,""+ childDataSnapshot.child(--ENTER THE KEY NAME eg. firstname or email etc.--).getValue());   //gives the value for given keyname
                    Jadwal text = childDataSnapshot.getValue(Jadwal.class);

                    Jadwal j = new Jadwal(childDataSnapshot.getKey(), text.dosen,text.hari,text.jam,text.matakuliah,text.ruang);

                    // adding movie to movies array
                    jadwalList.add(j);

                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
