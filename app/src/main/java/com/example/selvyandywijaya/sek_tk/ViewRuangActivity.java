package com.example.selvyandywijaya.sek_tk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selvyandywijaya.sek_tk.adapter.RuangAdapter;
import com.example.selvyandywijaya.sek_tk.model.Ruang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ViewRuangActivity extends AppCompatActivity {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRuangRef = mRootRef.child("ruang");

    private List<Ruang> ruangList = new ArrayList<Ruang>();
    private ListView listView;
    private RuangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ruang);

  //      Ruang product = new Ruang();
  //      product.nama = "assasin" ;
        // adding movie to movies array
  //      ruangList.add(product);

    //    Ruang produc = new Ruang();
    //    produc.nama = "saber" ;
        // adding movie to movies array
    //    ruangList.add(produc);

        listView = (ListView) findViewById(R.id.lv);
        adapter = new RuangAdapter(this, ruangList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String Name = ruangList.get(position).nama;
                String RKey = ruangList.get(position).key;
            /*    String ThumbnailUrl = movieList.get(position).thumbnailUrl;
                String Price = movieList.get(position).Price;
                String Stock = movieList.get(position).Stock;
                int ReleaseYear = movieList.get(position).ReleaseYear;

                Intent intent = new Intent( getApplicationContext(), ProductActivity.class);
                intent.putExtra("ProductName",ProductName);
                intent.putExtra("ThumbnailUrl", ThumbnailUrl);
                intent.putExtra("Price",Price);
                intent.putExtra("Stock",Stock);
                intent.putExtra("ReleaseYear",ReleaseYear);
                startActivity(intent);*/


                Toast.makeText(getBaseContext(), Name, Toast.LENGTH_LONG).show();

                Intent intent = new Intent( getApplicationContext(), ViewJadwalActivity.class);
                intent.putExtra("RuangName",Name);
                intent.putExtra("keyRuang", RKey);
                startActivity(intent);


            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:MM");
        String strDate = "Current Time : " + mdformat.format(calendar.getTime());

        TextView cltk = findViewById(R.id.clock);
        cltk.setText(strDate);

        mRuangRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ruangList.clear();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                 //   Log.v(TAG,""+ childDataSnapshot.getKey()); //displays the key for the node
                 // Log.v(TAG,""+ childDataSnapshot.child(--ENTER THE KEY NAME eg. firstname or email etc.--).getValue());   //gives the value for given keyname
                    Ruang text = childDataSnapshot.getValue(Ruang.class);

                    Ruang product = new Ruang(childDataSnapshot.getKey() ,text.nama, text.ImgUri,text.cap ,text.shift);
                    //product.nama = text.nama ;
                    // adding movie to movies array
                    ruangList.add(product);

                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
