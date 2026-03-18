package com.example.MoveOn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.FoodsItemClickListener;
import com.example.MoveOn.Model.DataFoods;
import com.example.MoveOn.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DataFoodsAdapter extends RecyclerView.Adapter<DataFoodsAdapter.MyViewHolder> {

    Context context ;
    List<DataFoods> mData;
    FoodsItemClickListener foodsItemClickListener;

    public DataFoodsAdapter(Context context, List<DataFoods> mData, FoodsItemClickListener listener) {
        this.context = context;
        this.mData = mData;
        foodsItemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_foods,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        holder.TopicFoods.setText(mData.get(i).getToppic_fd());
        Picasso.with(context)
                .load(ApiClient.resolveUrl(mData.get(i).getPicture_fd()))
                .fit()
                .into(holder.ImgFoods);

    }

    @Override
    public int getItemCount() { return mData.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView TopicFoods;
        private ImageView ImgFoods;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TopicFoods = itemView.findViewById(R.id.item_title);
            ImgFoods = itemView.findViewById(R.id.item_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    foodsItemClickListener.onFoodsClick(mData.get(getAdapterPosition()),ImgFoods);
                }
            });
        }
    }
}
