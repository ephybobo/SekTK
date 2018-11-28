package com.example.selvyandywijaya.sek_tk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.selvyandywijaya.sek_tk.adapter.ManageJadwalAdapter;
import com.example.selvyandywijaya.sek_tk.adapter.ManageRuangAdapter;
import com.example.selvyandywijaya.sek_tk.model.Jadwal;
import com.example.selvyandywijaya.sek_tk.model.Ruang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManageRuangActivity extends AppCompatActivity {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRuangRef = mRootRef.child("ruang");

    private List<Ruang> ruangList = new ArrayList<Ruang>();
    private ListView listView;
    private ManageRuangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_ruang);

        listView = (ListView) findViewById(R.id.lv);
        adapter = new ManageRuangAdapter(this, ruangList);
        listView.setAdapter(adapter);
    }


    @Override
    protected void onStart(){
        super.onStart();

        // Get the Intent that started this activity and extract the string
        // Intent intent = getIntent();
        // String room = intent.getStringExtra("RuangName");

        // TextView txtName = findViewById(R.id.NamaRuang);
        // txtName.setText(room);
        //.orderByChild("ruang").equalTo(room)
        mRuangRef.orderByChild("ruang").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    //   Log.v(TAG,""+ childDataSnapshot.getKey()); //displays the key for the node
                    // Log.v(TAG,""+ childDataSnapshot.child(--ENTER THE KEY NAME eg. firstname or email etc.--).getValue());   //gives the value for given keyname
                    Ruang text = childDataSnapshot.getValue(Ruang.class);

                    Ruang j = new Ruang(text.nama);

                    // adding movie to movies array
                    ruangList.add(j);

                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
