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
import com.example.MoveOn.Model.DataNews;
import com.example.MoveOn.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReadnewsAdapter extends RecyclerView.Adapter<ReadnewsAdapter.MyViewHolder>{

    private List<DataNews> readnewz;
    private Context context;


    public ReadnewsAdapter(List<DataNews> readnewz,Context context) {
        this.readnewz = readnewz;
        this.context = context;
    }


    @NonNull
    @Override
    public ReadnewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.listreadnews,viewGroup,false);
        return new ReadnewsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadnewsAdapter.MyViewHolder holder, int i) {
        holder.topic.setText(readnewz.get(i).getTopic_news());
        holder.descrip.setText(readnewz.get(i).getDescription_news());
        Picasso.with(context).load(ApiClient.resolveUrl(readnewz.get(i).getPicture_news())).fit().into(holder.picnews);

    }

    @Override
    public int getItemCount() {
        return readnewz.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView topic,descrip;
        ImageView picnews;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            picnews = (ImageView) itemView.findViewById(R.id.read_picture);
            topic = (TextView) itemView.findViewById(R.id.read_topic);
            descrip = (TextView) itemView.findViewById(R.id.read_descrip);
        }
    }
}
