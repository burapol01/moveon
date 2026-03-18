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
import com.example.MoveOn.Model.DataExercise;
import com.example.MoveOn.R;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class ReadYogaAdapter extends RecyclerView.Adapter<ReadYogaAdapter.MyViewHolder> {

    private List<DataExercise> readEx;
    private Context context;

    public ReadYogaAdapter(List<DataExercise> readEx, Context context) {
        this.readEx = readEx;
        this.context = context;
    }

    @NonNull
    @Override
    public ReadYogaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listreadyoga,viewGroup,false);
        return new ReadYogaAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadYogaAdapter.MyViewHolder holder, int i) {
        holder.category_ex.setText(readEx.get(i).getName_del());
        holder.read_name_ex.setText(readEx.get(i).getName_ex());
        holder.read_descrip_ex.setText(readEx.get(i).getDescription_ex());

        Glide.with(context)
                .load(ApiClient.resolveUrl(readEx.get(i).getPicture_ex()))
                .into(holder.read_picture_ex);
    }

    @Override
    public int getItemCount() {
        return readEx.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView category_ex,read_name_ex,read_descrip_ex;
        GifImageView read_picture_ex;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            category_ex = (TextView) itemView.findViewById(R.id.category_ex);
            read_picture_ex = (GifImageView) itemView.findViewById(R.id.read_picture_ex);
            read_name_ex = (TextView) itemView.findViewById(R.id.read_name_ex);
            read_descrip_ex = (TextView) itemView.findViewById(R.id.read_descrip_ex);
        }
    }
}
