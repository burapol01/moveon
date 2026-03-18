package com.example.MoveOn.Interface;

import android.widget.ImageView;

import com.example.MoveOn.Model.DataFoods;

public interface FoodsItemClickListener {

    void onFoodsClick(DataFoods foods, ImageView foodsImageView); // we will need the imageview to make the shared animation between the two activity

}
