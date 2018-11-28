package com.example.selvyandywijaya.sek_tk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selvyandywijaya.sek_tk.R;
import com.example.selvyandywijaya.sek_tk.model.Jadwal;
import com.example.selvyandywijaya.sek_tk.model.Ruang;

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
            convertView = inflater.inflate(R.layout.list_manage_ruang , null);
/*
        if (mImageLoader == null)
            mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);*/
        final TextView ruang = (TextView) convertView.findViewById(R.id.ruang);
        Button Edit = (Button) convertView.findViewById(R.id.JadwalEdit);
        Button Delete = (Button) convertView.findViewById(R.id.JadwalDelete);

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
                Toast.makeText(context, "buttonEdit"+ ruang.getText().toString() + position , Toast.LENGTH_LONG).show();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //View tempview = (View) holder.btn_plus.getTag(R.integer.btn_plus_view);
                // TextView tv = (TextView) tempview.findViewById(R.id.number);
                //Integer pos = (Integer) holder.btn_plus.getTag(R.integer.btn_plus_pos);

                //int number = Integer.parseInt(tv.getText().toString()) + 1;
                //tv.setText(String.valueOf(number));

                // MainActivity.modelArrayList.get(pos).setNumber(number);
                Toast.makeText(context, "buttonDelete"+ ruang.getText().toString() + position , Toast.LENGTH_LONG).show();
            }
        });

        // getting movie data for the row
        Ruang m = Items.get(position);

        // thumbnail image
        //thumbNail.setImageUrl(m.thumbnailUrl, mImageLoader);

        // title
        ruang.setText(m.nama);

        return convertView;

    }

}
