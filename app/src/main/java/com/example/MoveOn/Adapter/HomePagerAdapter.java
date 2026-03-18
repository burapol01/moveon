package com.example.MoveOn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Model.DataFoods;
import com.example.MoveOn.Model.DataNews;
import com.example.MoveOn.Model.Slide;
import com.example.MoveOn.R;
import com.squareup.picasso.Picasso;


import java.util.List;

public class HomePagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<DataNews> lstFoods;

    public HomePagerAdapter(Context mContext, List<DataNews> lstFoods) {
        this.mContext = mContext;
        this.lstFoods = lstFoods;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.slide_item, null);

        ImageView slideImg = slideLayout.findViewById(R.id.slide_img);
        TextView slideText = slideLayout.findViewById(R.id.slide_title);

        Picasso.with(mContext)
                .load(ApiClient.resolveUrl(lstFoods.get(position).getPicture_news()))
                .fit()
                .into(slideImg);
        slideText.setText(lstFoods.get(position).getTopic_news());


        container.addView(slideLayout);
        return slideLayout;

    }

    @Override
    public int getCount() {
        return lstFoods.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
