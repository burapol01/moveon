package com.example.MoveOn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MoveOn.Activity.BMI;
import com.example.MoveOn.R;
import com.example.MoveOn.item.ChildItem;
import com.example.MoveOn.item.SectionItem;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList mItem;
    private final int SECTION_ITEM = 0;
    private final int CHILD_ITEM = 1;

    public CustomAdapter(Context context, ArrayList items) {
        mContext = context;
        mItem = items;

    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }



    @Override
    public int getItemViewType(int position) {
        if (mItem.get(position) instanceof SectionItem) {
            return SECTION_ITEM;
        } else if (mItem.get(position) instanceof ChildItem) {
            return CHILD_ITEM;
        }
        return -1;
    }
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(mContext);
            final RecyclerView.ViewHolder vHolder;
            if (viewType == SECTION_ITEM){
                final View v = inflater.inflate(R.layout.item_section,parent, false);

                vHolder = new SectionHolder(v);
                return vHolder;
            } else if (viewType == CHILD_ITEM){
                final View v = inflater.inflate(R.layout.item_child,parent, false);

                vHolder = new ChildHolder(v);

              v.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      int pos = vHolder.getAdapterPosition();
                      if (pos != RecyclerView.NO_POSITION){
                          TextView tv = (TextView)v.findViewById(R.id.textView_child);

                          Intent Intent = new Intent(mContext, BMI.class);
                          mContext.startActivity(Intent);

                          Toast.makeText(mContext, tv.getText().toString(),Toast.LENGTH_SHORT).show();
                      }
                  }
              });
                return vHolder;
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vholder, int position) {

            int type = getItemViewType(position);
            if (type == SECTION_ITEM) {
                SectionItem item = (SectionItem) mItem.get(position);
                SectionHolder sectionHolder = (SectionHolder) vholder;
                sectionHolder.textView.setText(item.sectionText);
            } else if (type == CHILD_ITEM) {
                ChildItem item = (ChildItem) mItem.get(position);
                ChildHolder ChildHolder = (ChildHolder) vholder;
                ChildHolder.textView.setText(item.itemText);

            }
        }
}
