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
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder> {


    List<UserModel> profile;
    private Context context;

    public ProfileAdapter(List<UserModel> profile, Context context) {
        this.profile = profile;
        this.context = context;

    }

    @NonNull
    @Override
    public ProfileAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
        return new ProfileAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.MyViewHolder holder, int position) {
        holder.user_username_p.setText(profile.get(position).getUser_username());
        holder.user_email.setText(profile.get(position).getEmail());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.logo);
        requestOptions.error(R.drawable.logo);

        Glide.with(context)
                .load(profile.get(position).getPicture())
                .apply(requestOptions)
                .into(holder.picture_profile);
    }

    @Override
    public int getItemCount() {
        return profile.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView picture_profile;
        private TextView user_username_p, user_email;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            picture_profile = itemView.findViewById(R.id.picture_profile);
            user_username_p = itemView.findViewById(R.id.user_username_p);
            user_email = itemView.findViewById(R.id.user_email);
        }
    }
}
