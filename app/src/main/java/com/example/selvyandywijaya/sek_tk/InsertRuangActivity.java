package com.example.selvyandywijaya.sek_tk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selvyandywijaya.sek_tk.model.Ruang;
import com.example.selvyandywijaya.sek_tk.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertRuangActivity extends AppCompatActivity {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRuangRef = mRootRef.child("ruang");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_ruang);

        final Button SubmitRuang = findViewById(R.id.SubmitRuang);
        SubmitRuang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                EditText NamaRuang = findViewById(R.id.NamaRuang);

                String newRuang = mRuangRef.push().getKey();

                // creating user object
                Ruang r = new Ruang(NamaRuang.getText().toString());

                // pushing user to 'users' node using the userId
                mRuangRef.child(newRuang).setValue(r);

                Intent intent = new Intent(getApplication(), ViewRuangActivity.class);
                finish();
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Mengirim Data" , Toast.LENGTH_LONG).show();

            }
        });
    }
}
