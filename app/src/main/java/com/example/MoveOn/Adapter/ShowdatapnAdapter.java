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
import com.example.MoveOn.Model.Datapersonal;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowdatapnAdapter extends RecyclerView.Adapter<ShowdatapnAdapter.MyViewHolder> {

    List<Datapersonal> datapn;
    private Context context;

    public ShowdatapnAdapter (List<Datapersonal> datapn, Context context) {
        this.datapn = datapn;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_showdatapn, parent, false);
        return new ShowdatapnAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title_sh.setText(datapn.get(position).getDescript_title());
        holder.name_sh.setText(datapn.get(position).getName());
        holder.lastname_sh.setText(datapn.get(position).getLastname());
        holder.sex_sh.setText(datapn.get(position).getDescript_sex());
        holder.status_sh.setText(datapn.get(position).getDescript_stat());
        holder.weight_sh.setText(datapn.get(position).getWeight());
        holder.height_sh.setText(datapn.get(position).getHeight());
        holder.birth_sh.setText(datapn.get(position).getBirth());
        holder.address_sh.setText(datapn.get(position).getAddress());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.logo);
        requestOptions.error(R.drawable.logo);

        Glide.with(context)
                .load(datapn.get(position).getPicture())
                .apply(requestOptions)
                .into(holder.mPicture1);

    }

    @Override
    public int getItemCount() {
        return datapn.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView mPicture1;
        private TextView title_sh, name_sh, lastname_sh, sex_sh, status_sh, weight_sh, height_sh, birth_sh, address_sh;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mPicture1 = itemView.findViewById(R.id.picture1);
            title_sh = itemView.findViewById(R.id.title_sh);
            name_sh = itemView.findViewById(R.id.name_sh);
            lastname_sh = itemView.findViewById(R.id.lastname_sh);
            sex_sh = itemView.findViewById(R.id.sex_sh);
            status_sh = itemView.findViewById(R.id.status_sh);
            weight_sh = itemView.findViewById(R.id.weight_sh);
            height_sh = itemView.findViewById(R.id.height_sh);
            birth_sh = itemView.findViewById(R.id.birth_sh);
            address_sh = itemView.findViewById(R.id.address_sh);

        }
    }
}
