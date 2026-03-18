package com.example.MoveOn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.MoveOn.Model.History;
import com.example.MoveOn.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    List<History> Workoutday;
    private Context context;


    public HistoryAdapter(List<History> Workoutday, Context context) {
        this.Workoutday = Workoutday;
        this.context = context;

    }


    @NonNull
    @Override
    public HistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_history, parent, false);
        return new HistoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.MyViewHolder holder, int position) {
        holder.user_username.setText(Workoutday.get(position).getUser_username());
        holder.name_del.setText(Workoutday.get(position).getName_del());
        holder.date_time.setText(Workoutday.get(position).getDate_time());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.logo);
        requestOptions.error(R.drawable.logo);

        Glide.with(context)
                .load(Workoutday.get(position).getPicture())
                .apply(requestOptions)
                .into(holder.mPicture);

    }

    @Override
    public int getItemCount() {
        return Workoutday.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mPicture;
        private TextView user_username, name_del, date_time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mPicture = itemView.findViewById(R.id.picture);
            user_username = itemView.findViewById(R.id.name);
            name_del = itemView.findViewById(R.id.type);
            date_time = itemView.findViewById(R.id.date);
        }
    }
}
