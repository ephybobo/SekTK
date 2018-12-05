package com.example.selvyandywijaya.sek_tk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.selvyandywijaya.sek_tk.model.Ruang;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateRuangActivity extends AppCompatActivity {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRuangRef = mRootRef.child("ruang");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ruang);

        final EditText NamaRuang = findViewById(R.id.NamaRuang);


        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        final String nama = intent.getStringExtra("NamaRuang");
        final String key = intent.getStringExtra("keyRuang");

        NamaRuang.setText(nama);


        final Button SubmitRuang = findViewById(R.id.SubmitRuang);
        SubmitRuang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                // creating user object
                //Ruang r = new Ruang(NamaRuang.getText().toString());

                // pushing user to 'users' node using the userId
                mRuangRef.child(key).child("nama").setValue(NamaRuang.getText().toString());

                Intent intent = new Intent(getApplication(), ManageRuangActivity.class);
                finish();
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Mengirim Data"+key , Toast.LENGTH_LONG).show();

            }
        });

    }
}
