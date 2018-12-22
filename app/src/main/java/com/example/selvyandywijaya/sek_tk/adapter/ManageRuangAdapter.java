package com.example.selvyandywijaya.sek_tk.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.selvyandywijaya.sek_tk.R;
import com.example.selvyandywijaya.sek_tk.UpdateRuangActivity;
import com.example.selvyandywijaya.sek_tk.model.Ruang;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;
import java.util.List;

/**
 * Created by ASUS on 11/28/2018.
 */

public class ManageRuangAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Ruang> Items;
    // Get the ImageLoader through your singleton class.
    //ImageLoader mImageLoader = VolleySingleton.getInstance(context).getImageLoader();

    public ManageRuangAdapter(Context context, List<Ruang> Items) {
        this.context = context;
        this.Items = Items;
    }

    @Override
    public int getCount() { return Items.size(); }

    @Override
    public Object getItem(int position) {
        return Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_manage_ruang, null);
/*
        if (mImageLoader == null)
            mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);*/
        final TextView ruang = (TextView) convertView.findViewById(R.id.ruang);
        Button Edit = (Button) convertView.findViewById(R.id.JadwalEdit);
        Button Delete = (Button) convertView.findViewById(R.id.JadwalDelete);

        ImageView avail = convertView.findViewById(R.id.statusAvailable);
        ImageView img = convertView.findViewById(R.id.RuangImg);

        // getting movie data for the row
        final Ruang m = Items.get(position);

        Glide.with(context)
                .load(m.ImgUri)
                .placeholder(R.drawable.sek)
                .error(R.drawable.sek)
                .into(img);

        // thumbnail image
        //thumbNail.setImageUrl(m.thumbnailUrl, mImageLoader);

        // title
        ruang.setText(m.nama);

        if(Collections.frequency(m.shift,"terpakai")>=3) {
            avail.setImageResource(R.drawable.statusiconunavailable);
            //statpak.setText("Sedang Dipakai");
        }else {
            avail.setImageResource(R.drawable.statusiconavailable);
            //statpak.setText("Kosong");
        }

        //  Edit.setTag();
        //Edit.setTag(R.integer.btn_plus_pos, position);
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //View tempview = (View) holder.btn_plus.getTag(R.integer.btn_plus_view);
                // TextView tv = (TextView) tempview.findViewById(R.id.number);
                //Integer pos = (Integer) holder.btn_plus.getTag(R.integer.btn_plus_pos);

                //int number = Integer.parseInt(tv.getText().toString()) + 1;
                //tv.setText(String.valueOf(number));

                // MainActivity.modelArrayList.get(pos).setNumber(number);

                Intent intent = new Intent(context, UpdateRuangActivity.class);
                intent.putExtra("NamaRuang",m.nama);
                intent.putExtra("keyRuang",m.key);
                //startActivity(intent);
                context.startActivity(intent);

                Toast.makeText(context, "buttonEdit"+ ruang.getText().toString() + position , Toast.LENGTH_LONG).show();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                final DatabaseReference mRuangRef = mRootRef.child("ruang");

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Hapus Ruang ?");
                // Add the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        mRuangRef.child(m.key).removeValue();
                        Toast.makeText(context, "buttonDeleteOK"+ ruang.getText().toString() + position , Toast.LENGTH_LONG).show();


                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        Toast.makeText(context, "buttonDeleteCancel"+ ruang.getText().toString() + position , Toast.LENGTH_LONG).show();
                    }
                });

                // Create the AlertDialog
                AlertDialog dialog = builder.create();

                dialog.show();

            }
        });



        return convertView;

    }

}
