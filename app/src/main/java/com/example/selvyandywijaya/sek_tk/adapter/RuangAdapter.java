package com.example.selvyandywijaya.sek_tk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.selvyandywijaya.sek_tk.R;
import com.example.selvyandywijaya.sek_tk.model.Ruang;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by ASUS on 11/1/2018.
 */

public class RuangAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Ruang> Items;
    // Get the ImageLoader through your singleton class.
    //ImageLoader mImageLoader = VolleySingleton.getInstance(context).getImageLoader();

    public RuangAdapter(Context context, List<Ruang> Items) {
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
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_ruang, null);
/*
        if (mImageLoader == null)
            mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);*/
        TextView name = (TextView) convertView.findViewById(R.id.NamaRuang);
        ImageView img = convertView.findViewById(R.id.RuangImg);
        ImageView avail = convertView.findViewById(R.id.statusAvailable);
        TextView statpak = convertView.findViewById(R.id.statusPakai);

        // getting movie data for the row
        Ruang m = Items.get(position);

        Glide.with(context)
                .load(m.ImgUri)
                .placeholder(R.drawable.sek)
                .error(R.drawable.sek)
                .into(img);

        // thumbnail image
        //thumbNail.setImageUrl(m.thumbnailUrl, mImageLoader);

        // title
        name.setText(m.nama);

        if(Collections.frequency(m.shift,"terpakai")>=3) {
            avail.setImageResource(R.drawable.statusiconunavailable);
            //statpak.setText("Sedang Dipakai");
        }else {
            avail.setImageResource(R.drawable.statusiconavailable);
            //statpak.setText("Kosong");
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH");
        String strDate = mdformat.format(calendar.getTime());

        int clk = Integer.parseInt(strDate);
        if( 8 <= clk && clk <11 ){
            statpak.setText(m.shift.get(0));
        }else if( 11 <= clk && clk <14 ){
            statpak.setText(m.shift.get(1));
        }else if( 14 <= clk && clk <18 ) {
            statpak.setText(m.shift.get(2));
        }else{
            statpak.setText("tidak terpakai");
        }



        return convertView;

    }

}
