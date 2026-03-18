package com.example.MoveOn.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.MoveOn.Adapter.RecyclerViewAdapter;
import com.example.MoveOn.Model.Exercise;
import com.example.MoveOn.R;

import java.util.ArrayList;
import java.util.List;

public class SelectYoga extends AppCompatActivity {
    List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_yoga);
        initData(); //ข้อมูลรูปถาพ

        recyclerView = (RecyclerView)findViewById(R.id.List_ex);
        adapter = new RecyclerViewAdapter(exerciseList,getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        exerciseList.add(new Exercise(R.drawable.half_pigeon, "ท่าที่ 1"));
        exerciseList.add(new Exercise(R.drawable.boat_pose, "ท่าที่ 2"));
        exerciseList.add(new Exercise(R.drawable.bow_pose, "ท่าที่ 3"));
        exerciseList.add(new Exercise(R.drawable.cobra_pose, "ท่าที่ 4"));
        exerciseList.add(new Exercise(R.drawable.crescent_lunge, "ท่าที่ 5"));
//        exerciseList.add(new Exercise(R.drawable.downward_facing_dog, "ท่าที่ 6"));
//        exerciseList.add(new Exercise(R.drawable.easy_pose, "ท่าที่ 7"));
//        exerciseList.add(new Exercise(R.drawable.low_lunge, "ท่าที่ 8"));
//        exerciseList.add(new Exercise(R.drawable.upward_bow, "ท่าที่ 9"));
//        exerciseList.add(new Exercise(R.drawable.warrior_pose, "ท่าที่ 10"));
//        exerciseList.add(new Exercise(R.drawable.warrior_pose_2, "ท่าที่ 11"));


//        exerciseList.add(new Exercise(R.drawable.half_pigeon, "ท่าที่ 1"));
//        exerciseList.add(new Exercise(R.drawable.boat_pose, "ท่าที่ 2"));
//        exerciseList.add(new Exercise(R.drawable.bow_pose, "ท่าที่ 3"));
//        exerciseList.add(new Exercise(R.drawable.cobra_pose, "ท่าที่ 4"));
//        exerciseList.add(new Exercise(R.drawable.crescent_lunge, "ท่าที่ 5"));
//        exerciseList.add(new Exercise(R.drawable.downward_facing_dog, "ท่าที่ 6"));
//        exerciseList.add(new Exercise(R.drawable.easy_pose, "ท่าที่ 7"));
//        exerciseList.add(new Exercise(R.drawable.low_lunge, "ท่าที่ 8"));
//        exerciseList.add(new Exercise(R.drawable.upward_bow, "ท่าที่ 9"));
//        exerciseList.add(new Exercise(R.drawable.warrior_pose, "ท่าที่ 10"));
//        exerciseList.add(new Exercise(R.drawable.warrior_pose_2, "ท่าที่ 11"));
    }
}
