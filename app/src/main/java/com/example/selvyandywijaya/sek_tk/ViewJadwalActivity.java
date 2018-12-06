package com.example.selvyandywijaya.sek_tk;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selvyandywijaya.sek_tk.adapter.CarouselImageAdapter;
import com.example.selvyandywijaya.sek_tk.adapter.JadwalAdapter;
import com.example.selvyandywijaya.sek_tk.model.Jadwal;
import com.example.selvyandywijaya.sek_tk.model.Ruang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewJadwalActivity extends AppCompatActivity {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRuangRef = mRootRef.child("ruang");
    DatabaseReference mJadwalRef = mRootRef.child("jadwal");

    private List<Jadwal> jadwalList = new ArrayList<Jadwal>();
    private ListView listView;
    private JadwalAdapter adapter;
    double lat,lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jadwal);

        listView = (ListView) findViewById(R.id.lv);
        adapter = new JadwalAdapter(this, jadwalList);
        listView.setAdapter(adapter);


    }

    @Override
    protected void onStart(){
        super.onStart();

        //String[] photoUrls = {"https://firebasestorage.googleapis.com/v0/b/sektk-71e37.appspot.com/o/images%2F82b1838e-c2b6-47ae-9379-6a7d8170ecc2?alt=media&token=5c8ae04c-e348-49b9-bff7-806286bf4c53",
        //        "https://firebasestorage.googleapis.com/v0/b/sektk-71e37.appspot.com/o/images%2F82b1838e-c2b6-47ae-9379-6a7d8170ecc2?alt=media&token=5c8ae04c-e348-49b9-bff7-806286bf4c53"};



        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String room = intent.getStringExtra("RuangName");
        String rkey = intent.getStringExtra("keyRuang");

        TextView txtRuang = findViewById(R.id.NamaRuang);
        txtRuang.setText(room);

        final ImageView imgRuang = findViewById(R.id.imgRuang);

        Toast.makeText(getApplicationContext(), "view " + room , Toast.LENGTH_LONG).show();
        mRuangRef.child(rkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    Ruang text = dataSnapshot.getValue(Ruang.class);

                    lat = text.lat; lng = text.lng;

                GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
                ArrayList<String> imlist = dataSnapshot.child("image").getValue(t);


                ViewPager viewPager = findViewById(R.id.vp_photogallery);

                if (viewPager != null) {
                    viewPager.setAdapter(new CarouselImageAdapter(getApplication(), imlist ));
                }

/*
                    Glide.with(getApplicationContext())
                            .load(text.ImgUri)
                            .placeholder(R.drawable.sek)
                            .error(R.drawable.sek)
                            .into(imgRuang);*/


//                Toast.makeText(getApplicationContext(), "Selamat datang j " + text.nama , Toast.LENGTH_LONG).show();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        mJadwalRef.orderByChild("ruang").equalTo(room).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    //   Log.v(TAG,""+ childDataSnapshot.getKey()); //displays the key for the node
                    // Log.v(TAG,""+ childDataSnapshot.child(--ENTER THE KEY NAME eg. firstname or email etc.--).getValue());   //gives the value for given keyname
                    Jadwal text = childDataSnapshot.getValue(Jadwal.class);

                    Jadwal j = new Jadwal(text.dosen,text.hari,text.jam,text.matakuliah,text.ruang);

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

    public void ViewMap(View view)
    {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("latroom", lat);
        intent.putExtra("lngroom",lng);
        startActivity(intent);
    }

}
