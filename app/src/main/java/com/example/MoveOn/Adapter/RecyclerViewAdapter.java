package com.example.MoveOn.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MoveOn.Interface.ItemClickListener;
import com.example.MoveOn.Model.Exercise;
import com.example.MoveOn.R;
import com.example.MoveOn.Activity.ViewGames;

import java.util.List;

//ประกาศ class RecyclerViewAdapter สำหรับคลิิก
class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    //ประกาศตัวแปร 3 ส่วน
    //1.รูปภาพ 2. ข้อความ 3.ไอเทมสำหรับคลิก
    public ImageView gifImageView;
    //    public ImageView image; //สำหรับรูปปกติ
    public TextView text;
    private ItemClickListener itemClickListener;

    //กำหนด ViewHolder เพื่อให้มันถือ ไอเทมไว้
    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
//      image = (ImageView) itemView.findViewById(R.id.ex_image);
        text = (TextView) itemView.findViewById(R.id.ex_name);
        gifImageView = (ImageView) itemView.findViewById(R.id.ex_image);

        itemView.setOnClickListener(this);
    }

    //ตั้งค่าไอเทม
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    //ไอเทมถูกคลิกที่นี่
    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition());

    }
}

//ประกาศ class RecyclerViewAdapter แสดงผล
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Exercise> exerciseList;
    private Context context;

    //ถือข้อมูลไว้ที่นี่
    public RecyclerViewAdapter(List<Exercise> exercise, Context context) {
        this.exerciseList = exercise;
        this.context = context;
    }

    //แสดงผลหน้า UI
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_exercise, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    //กำหนดไอเทมเพื่อแสดงผลหน้า UI
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
//        holder.image.setImageResource(exerciseList.get(position).getImage_id());
        holder.gifImageView.setImageResource(exerciseList.get(position).getImage_id());
        holder.text.setText(exerciseList.get(position).getName());


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                //call to new Activity
                Toast.makeText(context, "Click to " + exerciseList.get(position).getName(), Toast.LENGTH_SHORT).show();

                Intent Intent = new Intent(context, ViewGames.class);
                Intent.putExtra("image_id", exerciseList.get(position).getImage_id());
                Intent.putExtra("name", exerciseList.get(position).getName());
                context.startActivity(Intent);

            }
        });

    }

    //ส่งค่าคืน
    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
