package com.example.MoveOn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Model.DataFoods;
import com.example.MoveOn.Model.DataNews;
import com.example.MoveOn.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodsPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<DataFoods> mListfoods;

    public FoodsPagerAdapter(Context mContext, List<DataFoods> mListfoods) {
        this.mContext = mContext;
        this.mListfoods = mListfoods;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.slide_item, null);

        ImageView slideImg = slideLayout.findViewById(R.id.slide_img);
        TextView slideText = slideLayout.findViewById(R.id.slide_title);

        Picasso.with(mContext)
                .load(ApiClient.resolveUrl(mListfoods.get(position).getPicture_fd()))
                .fit()
                .into(slideImg);
        slideText.setText(mListfoods.get(position).getToppic_fd());


        container.addView(slideLayout);
        return slideLayout;

    }

    @Override
    public int getCount() {
        return mListfoods.size();
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
