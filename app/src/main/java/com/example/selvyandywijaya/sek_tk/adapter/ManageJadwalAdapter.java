package com.example.selvyandywijaya.sek_tk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selvyandywijaya.sek_tk.MainActivity;
import com.example.selvyandywijaya.sek_tk.R;
import com.example.selvyandywijaya.sek_tk.model.Jadwal;

import java.util.List;

/**
 * Created by ASUS on 11/14/2018.
 */

public class ManageJadwalAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Jadwal> Items;
    // Get the ImageLoader through your singleton class.
    //ImageLoader mImageLoader = VolleySingleton.getInstance(context).getImageLoader();

    public ManageJadwalAdapter(Context context, List<Jadwal> Items) {
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
            convertView = inflater.inflate(R.layout.list_manage_jadwal , null);
/*
        if (mImageLoader == null)
            mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);*/
        TextView ruang = (TextView) convertView.findViewById(R.id.ruang);
       final TextView matakuliah = (TextView) convertView.findViewById(R.id.matakuliah);
        TextView dosen = (TextView) convertView.findViewById(R.id.dosen);
        TextView hari = (TextView) convertView.findViewById(R.id.hari);
        TextView jam = (TextView) convertView.findViewById(R.id.jam);
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
                Toast.makeText(context, "buttonEdit"+ matakuliah.getText().toString() + position , Toast.LENGTH_LONG).show();
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
                Toast.makeText(context, "buttonDelete"+ matakuliah.getText().toString() + position , Toast.LENGTH_LONG).show();
            }
        });

        // getting movie data for the row
        Jadwal m = Items.get(position);

        // thumbnail image
        //thumbNail.setImageUrl(m.thumbnailUrl, mImageLoader);

        // title
        ruang.setText(m.ruang);
        matakuliah.setText(m.matakuliah);
        dosen.setText(m.dosen);
        hari.setText(m.hari);
        jam.setText(m.jam);

        return convertView;

    }

}