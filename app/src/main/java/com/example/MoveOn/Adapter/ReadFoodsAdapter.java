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
import com.example.MoveOn.Model.DataFoods;
import com.example.MoveOn.Model.DataNews;
import com.example.MoveOn.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReadFoodsAdapter extends RecyclerView.Adapter<ReadFoodsAdapter.MyViewHolder> {

    private List<DataFoods> readfoods;
    private Context context;

    public ReadFoodsAdapter(List<DataFoods> readfoods, Context context) {
        this.readfoods = readfoods;
        this.context = context;
    }

    @NonNull
    @Override
    public ReadFoodsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listreadfoods,viewGroup,false);
        return new ReadFoodsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadFoodsAdapter.MyViewHolder holder, int i) {

        holder.catfoods.setText(readfoods.get(i).getName_food());
        holder.mealtime_fd.setText(readfoods.get(i).getMealtime());

        holder.topic.setText(readfoods.get(i).getToppic_fd());
        holder.descrip.setText(readfoods.get(i).getDescription_fd());
        Picasso.with(context).load(ApiClient.resolveUrl(readfoods.get(i).getPicture_fd())).fit().into(holder.picfoods);

        holder.category_dk.setText(readfoods.get(i).getName_drk());

        holder.topicdk.setText(readfoods.get(i).getToppic_dk());
        holder.descripdk.setText(readfoods.get(i).getDescription_dk());
        Picasso.with(context).load(ApiClient.resolveUrl(readfoods.get(i).getPicture_dk())).fit().into(holder.picdirnk);

    }

    @Override
    public int getItemCount() { return readfoods.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView topic,descrip,topicdk,descripdk,catfoods,mealtime_fd,category_dk;
        ImageView picfoods,picdirnk;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            catfoods = (TextView) itemView.findViewById(R.id.catfoods);
            mealtime_fd = (TextView) itemView.findViewById(R.id.mealtime_fd);

            picfoods = (ImageView) itemView.findViewById(R.id.read_picture_fd);
            topic = (TextView) itemView.findViewById(R.id.read_topic_fd);
            descrip = (TextView) itemView.findViewById(R.id.read_descrip_fd);

            category_dk = (TextView) itemView.findViewById(R.id.category_dk);

            picdirnk = (ImageView) itemView.findViewById(R.id.read_picture_dk);
            topicdk = (TextView) itemView.findViewById(R.id.read_topic_dk);
            descripdk = (TextView) itemView.findViewById(R.id.read_descrip_dk);
        }
    }
}
