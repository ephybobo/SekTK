package com.example.selvyandywijaya.sek_tk.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.selvyandywijaya.sek_tk.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ASUS on 12/4/2018.
 */

public class CarouselImageAdapter extends PagerAdapter {

    private static final String TAG = "ImageViewPage";
    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<String> mResources;

    public CarouselImageAdapter(Context context,  ArrayList<String> resources) {
        mContext = context;
        mResources = resources;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d(TAG,
                "instantiateItem() called with: " + "container = [" + container + "], position = [" + position + "]");

        View itemView = mLayoutInflater.inflate(R.layout.item_pager, container, false);

        Log.d(TAG, "load in gallery:" + mResources.get(position) + "#end");
        final ImageView ivPhoto = (ImageView) itemView.findViewById(R.id.iv_photo);

        if (!mResources.get(position).equals("")){
            Glide.with(mContext)
                    .load(mResources.get(position).trim())
                    .crossFade()
                    .into(ivPhoto);
        }

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.d(TAG, "destroyItem() called with: " + "container = [" + container + "], position = [" + position
                + "], object = [" + object + "]");
        container.removeView((LinearLayout) object);
    }
}
