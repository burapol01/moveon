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

import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Model.Datapersonal;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class gmaesAdapter extends RecyclerView.Adapter<gmaesAdapter.MyViewHolder> {

    //ประกาศ
    private List<UserModel> datapn;
    private Context context;

    //ถือข้อมูลไว้เป็น list
    public gmaesAdapter(List<UserModel> datapn, Context context) {
        this.datapn = datapn;
        this.context = context;

    }

    //แสดงผล UI
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.listdatapn, viewGroup, false);
        return new MyViewHolder(view);
    }

    //Holder
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        holder.name.setText(datapn.get(i).getUser_username());
        holder.lastname.setText(datapn.get(i).getEmail());
    }

    //ส่งค่าคืน datapn
    @Override
    public int getItemCount() {
        return datapn.size();
    }

    //ประกาศ class MyViewHolder สำหรับใช้อ้างอิงหน้า UI
    public class MyViewHolder extends RecyclerView.ViewHolder {
        //ตัวแปร
        TextView name, lastname;
        ImageView picnews;
        Button toread;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            picnews = (ImageView) itemView.findViewById(R.id.list_picture);
//            topic = (TextView) itemView.findViewById(R.id.list_topic);
//            toread = (Button) itemView.findViewById(R.id.toread);
            name = (TextView) itemView.findViewById(R.id.name);
            lastname = (TextView) itemView.findViewById(R.id.lastname);


        }
    }
}
