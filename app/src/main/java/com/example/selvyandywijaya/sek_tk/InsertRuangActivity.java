package com.example.selvyandywijaya.sek_tk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selvyandywijaya.sek_tk.model.Ruang;
import com.example.selvyandywijaya.sek_tk.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class InsertRuangActivity extends AppCompatActivity {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRuangRef = mRootRef.child("ruang");

    private Button btnChoose, btnUpload;
    private ImageView imageView;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;

    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_ruang);

        final Button SubmitRuang = findViewById(R.id.SubmitRuang);
        SubmitRuang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*
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
    */
                uploadImage();
            }
        });

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        imageView = (ImageView) findViewById(R.id.imgView);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
          //  UUID.randomUUID().toString()
            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplication(), "Uploaded" + taskSnapshot.getDownloadUrl(), Toast.LENGTH_SHORT).show();
                            storeData( taskSnapshot.getDownloadUrl().toString() );
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplication(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    public void storeData(String imgUrl){
        // Code here executes on main thread after user presses button
        EditText NamaRuang = findViewById(R.id.NamaRuang);

        String newRuang = mRuangRef.push().getKey();

        // creating user object
        Ruang r = new Ruang(NamaRuang.getText().toString(), imgUrl ,-7.284855,112.796579);

        // pushing user to 'users' node using the userId
        mRuangRef.child(newRuang).setValue(r);

        Intent intent = new Intent(getApplication(), ViewRuangActivity.class);
        finish();
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Mengirim Data" , Toast.LENGTH_LONG).show();
    }


}
