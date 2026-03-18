package com.example.MoveOn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.MoveOn.Model.Videofile;
import com.example.MoveOn.R;

import java.util.List;

public class ViewVideoAdapter extends RecyclerView.Adapter<ViewVideoAdapter.MyViewHolder> {

    List<Videofile> viewvdo;
    private Context context;

    public ViewVideoAdapter(List<Videofile> viewvdo, Context context) {
        this.viewvdo = viewvdo;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewVideoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listdatavideoex,viewGroup,false);
        return new ViewVideoAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewVideoAdapter.MyViewHolder holder, int i) {
        holder.category_ex_vdo.setText(viewvdo.get(i).getName_del());
        holder.name_vdo.setText(viewvdo.get(i).getName_vd());
        holder.video_description.setText(viewvdo.get(i).getDescriptions_vd());

    }

    @Override
    public int getItemCount() {
        return viewvdo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name_vdo,video_description,category_ex_vdo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            category_ex_vdo = itemView.findViewById(R.id.category_ex_vdo);
            name_vdo = (TextView) itemView.findViewById(R.id.name_vdo);
            video_description = (TextView) itemView.findViewById(R.id.video_description);

        }


    }
}
