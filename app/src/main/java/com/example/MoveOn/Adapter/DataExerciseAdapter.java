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

public class DataExerciseAdapter extends RecyclerView.Adapter<DataExerciseAdapter.MyViewHolder> {

    private List<DataExercise> dataex;
    private Context context;
    ExerciseItemClickListener exerciseItemClickListener;

    public DataExerciseAdapter(Context context,List<DataExercise> dataex,ExerciseItemClickListener listener) {

        this.context = context;
        this.dataex = dataex;
        exerciseItemClickListener  = listener;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dataex,viewGroup,false);
        return new DataExerciseAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.name_ex.setText(dataex.get(i).getName_ex());

        Glide.with(context)
                .load(ApiClient.resolveUrl(dataex.get(i).getPicture_ex()))
                .into(holder.pic_ex);
//        Picasso.with(context).load(ApiClient.resolveUrl(dataex.get(i).getPicture_ex())).fit().into(holder.pic_ex);

    }

    @Override
    public int getItemCount() {
        return dataex.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        GifImageView pic_ex;
        TextView name_ex;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pic_ex = (GifImageView) itemView.findViewById(R.id.pic_ex);
            name_ex = (TextView) itemView.findViewById(R.id.name_ex);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exerciseItemClickListener.onExerciseClick(dataex.get(getAdapterPosition()),pic_ex);
                }
            });


        }
    }
}
