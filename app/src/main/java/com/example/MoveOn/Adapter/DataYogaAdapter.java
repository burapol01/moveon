package com.example.MoveOn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ExerciseItemClickListener;
import com.example.MoveOn.Model.DataExercise;
import com.example.MoveOn.R;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class DataYogaAdapter extends RecyclerView.Adapter<DataYogaAdapter.MyViewHolder> {

    private List<DataExercise> datayoga;
    private Context context;
    ExerciseItemClickListener exerciseItemClickListener;

    public DataYogaAdapter(Context context,List<DataExercise> datayoga,ExerciseItemClickListener listener) {
        this.context = context;
        this.datayoga = datayoga;
        exerciseItemClickListener = listener;
    }
    @NonNull
    @Override
    public DataYogaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dataex,viewGroup,false);
        return new DataYogaAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataYogaAdapter.MyViewHolder holder, int i) {
        holder.name_yoga.setText(datayoga.get(i).getName_ex());

        Glide.with(context)
                .load(ApiClient.resolveUrl(datayoga.get(i).getPicture_ex()))
                .into(holder.pic_yoga);
    }

    @Override
    public int getItemCount() { return datayoga.size(); }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        GifImageView pic_yoga;
        TextView name_yoga;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pic_yoga = (GifImageView) itemView.findViewById(R.id.pic_ex);
            name_yoga = (TextView) itemView.findViewById(R.id.name_ex);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exerciseItemClickListener.onExerciseClick(datayoga.get(getAdapterPosition()),pic_yoga);
                }
            });
        }
    }
}
