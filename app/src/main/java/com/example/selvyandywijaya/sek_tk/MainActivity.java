package com.example.selvyandywijaya.sek_tk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.selvyandywijaya.sek_tk.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mUserRef = mRootRef.child("user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart(){
        super.onStart();



    }


    public void main_menu (View view)
    {
        EditText Username = findViewById(R.id.inuser);
        final EditText Password = findViewById(R.id.inpass);

        mUserRef.child(Username.getText().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User text = dataSnapshot.getValue(User.class);

                if(Password.getText().toString().equals(text.password)){
                    Toast.makeText(getApplicationContext(), "Selamat datang " + text.nama , Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext() , MainMenuActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Pergi Sana !"+text.password+Password.getText().toString() , Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
