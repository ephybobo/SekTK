package com.example.selvyandywijaya.sek_tk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.selvyandywijaya.sek_tk.adapter.JadwalAdapter;
import com.example.selvyandywijaya.sek_tk.adapter.RuangAdapter;
import com.example.selvyandywijaya.sek_tk.model.Jadwal;
import com.example.selvyandywijaya.sek_tk.model.Ruang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewAllJadwalActivity extends AppCompatActivity {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mJadwalRef = mRootRef.child("Jadwal");

    private List<Jadwal> jadwalList = new ArrayList<Jadwal>();
    private ListView listView;
    private JadwalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_jadwal);

        listView = (ListView) findViewById(R.id.lv);
        adapter = new JadwalAdapter(this, jadwalList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onStart(){
        super.onStart();

        mJadwalRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                jadwalList.clear();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    //   Log.v(TAG,""+ childDataSnapshot.getKey()); //displays the key for the node
                    // Log.v(TAG,""+ childDataSnapshot.child(--ENTER THE KEY NAME eg. firstname or email etc.--).getValue());   //gives the value for given keyname
                    Jadwal text = childDataSnapshot.getValue(Jadwal.class);

                    Jadwal product = new Jadwal(text.dosen,text.hari,text.jam,text.matakuliah,text.ruang);
                    //product.nama = text.nama ;
                    // adding movie to movies array
                    jadwalList.add(product);

                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
