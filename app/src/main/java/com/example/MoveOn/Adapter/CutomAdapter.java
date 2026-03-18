package com.example.MoveOn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MoveOn.R;
import com.example.MoveOn.item.ChildItem;
import com.example.MoveOn.item.SectionItem;

import java.util.ArrayList;

class SectionHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public SectionHolder(@NonNull View v) {
        super(v);
        this.textView = (TextView) v.findViewById(R.id.tv_section);
    }
}

class ChildHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public ChildHolder(@NonNull View v) {
        super(v);
        this.textView = (TextView) v.findViewById(R.id.textView_child);
    }
}

