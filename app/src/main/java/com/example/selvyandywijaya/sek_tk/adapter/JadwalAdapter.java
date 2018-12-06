package com.example.selvyandywijaya.sek_tk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.selvyandywijaya.sek_tk.R;
import com.example.selvyandywijaya.sek_tk.model.Jadwal;

import java.util.List;

/**
 * Created by ASUS on 11/6/2018.
 */

public class JadwalAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Jadwal> Items;
    // Get the ImageLoader through your singleton class.
    //ImageLoader mImageLoader = VolleySingleton.getInstance(context).getImageLoader();

    public JadwalAdapter(Context context, List<Jadwal> Items) {
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
            convertView = inflater.inflate(R.layout.list_jadwal, null);
/*
        if (mImageLoader == null)
            mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);*/
        TextView matakuliah = (TextView) convertView.findViewById(R.id.matakuliah);
        TextView dosen = (TextView) convertView.findViewById(R.id.dosen);
        TextView hari = (TextView) convertView.findViewById(R.id.hari);
        TextView jam = (TextView) convertView.findViewById(R.id.jam);

        // getting movie data for the row
        Jadwal m = Items.get(position);

        // thumbnail image
        //thumbNail.setImageUrl(m.thumbnailUrl, mImageLoader);

        // title
        matakuliah.setText(m.matakuliah);
        dosen.setText(m.dosen);
        hari.setText(m.hari);
        jam.setText(m.jam);

        return convertView;

    }

}
