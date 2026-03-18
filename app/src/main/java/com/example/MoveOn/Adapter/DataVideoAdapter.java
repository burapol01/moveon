package com.example.MoveOn.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.MoveOn.ApiClient;

import com.example.MoveOn.Interface.VideoItemClickListener;
import com.example.MoveOn.Model.Videofile;
import com.example.MoveOn.R;

import com.google.android.exoplayer2.SimpleExoPlayer;


import java.util.List;

public class DataVideoAdapter extends RecyclerView.Adapter<DataVideoAdapter.MyViewHolder> {

    Context context;
    List<Videofile> videofiles;
    SimpleExoPlayer simpleExoPlayer;
    VideoItemClickListener videoItemClickListener;

    public DataVideoAdapter(Context context, List<Videofile> videofiles, VideoItemClickListener listener) {
        this.context = context;
        this.videofiles = videofiles;
        videoItemClickListener = listener;
    }

    @NonNull
    @Override
    public DataVideoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_videofile, viewGroup, false);
        return new DataVideoAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataVideoAdapter.MyViewHolder holder, int i) {


        holder.TopicVideo.setText(videofiles.get(i).getName_vd());


        Glide.with(context)
                .load(ApiClient.resolveUrl(videofiles.get(i).getPicture_video()))
                .into(holder.ImgVideo);


    }

    @Override
    public int getItemCount() {
        return videofiles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView TopicVideo;
        private ImageView ImgVideo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TopicVideo = itemView.findViewById(R.id.item_title_video);
            ImgVideo = itemView.findViewById(R.id.item_picvideo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    videoItemClickListener.onVideoClick(videofiles.get(getAdapterPosition()),ImgVideo);
                }
            });


        }


    }
}
