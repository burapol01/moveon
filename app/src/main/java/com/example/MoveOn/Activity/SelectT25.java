package com.example.MoveOn.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.MoveOn.Adapter.RecyclerViewAdapter;
import com.example.MoveOn.Model.Exercise;
import com.example.MoveOn.R;

import java.util.ArrayList;
import java.util.List;

public class SelectT25 extends AppCompatActivity {


    List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_t25);

        initData(); //ข้อมูลรูปถาพ

        recyclerView = (RecyclerView)findViewById(R.id.List_ex);
        adapter = new RecyclerViewAdapter(exerciseList,getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        exerciseList.add(new Exercise(R.drawable.ext25_1, "ท่าที่ 1"));
        exerciseList.add(new Exercise(R.drawable.ext25_6, "ท่าที่ 2"));
        exerciseList.add(new Exercise(R.drawable.ext25_9, "ท่าที่ 3"));
        exerciseList.add(new Exercise(R.drawable.ext25_7, "ท่าที่ 4"));
        exerciseList.add(new Exercise(R.drawable.ext25_4, "ท่าที่ 5"));
//        exerciseList.add(new Exercise(R.drawable.nex2, "ท่าที่ 6"));
//        exerciseList.add(new Exercise(R.drawable.nex3, "ท่าที่ 7"));
//        exerciseList.add(new Exercise(R.drawable.nex1, "ท่าที่ 8"));
//        exerciseList.add(new Exercise(R.drawable.nex1, "ท่าที่ 9"));
//        exerciseList.add(new Exercise(R.drawable.nex1, "ท่าที่ 10"));
    }

    //ข้อมูลรูปภาพเพื่อเเสดงผล
//    private void initData() {
//
//        exerciseList.add(new Exercise(R.drawable.ext25_1, "ท่าที่ 1"));
//        exerciseList.add(new Exercise(R.drawable.ext25_2, "ท่าที่ 2"));
//        exerciseList.add(new Exercise(R.drawable.ext25_3, "ท่าที่ 3"));
//        exerciseList.add(new Exercise(R.drawable.ext25_4, "ท่าที่ 4"));
//        exerciseList.add(new Exercise(R.drawable.ext25_5, "ท่าที่ 5"));
//        exerciseList.add(new Exercise(R.drawable.ext25_6, "ท่าที่ 6"));
//        exerciseList.add(new Exercise(R.drawable.ext25_7, "ท่าที่ 7"));
//        exerciseList.add(new Exercise(R.drawable.ext25_8, "ท่าที่ 8"));
//        exerciseList.add(new Exercise(R.drawable.ext25_9, "ท่าที่ 9"));
//        exerciseList.add(new Exercise(R.drawable.ext25_10, "ท่าที่ 10"));
//    }

}
