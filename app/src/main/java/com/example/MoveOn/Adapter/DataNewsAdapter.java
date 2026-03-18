package com.example.MoveOn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MoveOn.Activity.DataReadNews;
import com.example.MoveOn.Activity.NewsMain;
import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.DataNews;
import com.example.MoveOn.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataNewsAdapter extends RecyclerView.Adapter<DataNewsAdapter.MyViewHolder> {

    private final List<DataNews> dataNews;
    private final Context context;

    public DataNewsAdapter(List<DataNews> dataNews, Context context) {
        this.dataNews = dataNews;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listnews, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        DataNews item = dataNews.get(position);
        holder.topic.setText(item.getTopic_news());
        Picasso.with(context).load(ApiClient.resolveUrl(item.getPicture_news())).fit().into(holder.picnews);
        holder.toread.setOnClickListener(v -> {
            if (NewsMain.uid != null) {
                ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                Call<ApiResponse<DataNews>> call = apiInterface.setReadnews(item.getDatanews_id(), NewsMain.uid.getUser_id());
                call.enqueue(new Callback<ApiResponse<DataNews>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<DataNews>> call, Response<ApiResponse<DataNews>> response) {
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<DataNews>> call, Throwable t) {
                    }
                });
            }

            Intent toread = new Intent(context, DataReadNews.class);
            toread.putExtra("datanews_id", item.getDatanews_id());
            context.startActivity(toread);
        });
    }

    @Override
    public int getItemCount() {
        return dataNews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView topic;
        ImageView picnews;
        Button toread;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            picnews = itemView.findViewById(R.id.list_picture);
            topic = itemView.findViewById(R.id.list_topic);
            toread = itemView.findViewById(R.id.toread);
        }
    }
}
