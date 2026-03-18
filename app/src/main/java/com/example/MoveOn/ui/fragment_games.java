package com.example.MoveOn.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.MoveOn.Activity.ExerciseMain;
import com.example.MoveOn.Activity.DataYoga;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;

public class fragment_games extends Fragment {


    CardView start_t25,start_yoga;


public static fragment_games newInstance(){
    fragment_games fragment_games = new fragment_games();
    return fragment_games;
}

    //แสดงผล UI
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup View = (ViewGroup) inflater.inflate(
                R.layout.fragment_games, container, false);


        start_t25 = (CardView) View.findViewById(R.id.start_t25);
        start_yoga = (CardView) View.findViewById(R.id.start_yoga);


        catagoryex();


        return View; //ส่งค่ากลับให้ View
    }

    private void catagoryex() {
        start_t25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                Intent i = getActivity().getIntent();
                UserModel user_head = (UserModel) i.getSerializableExtra("user_head");
                Intent g = new Intent(getActivity(), ExerciseMain.class);
                g.putExtra("user_head", user_head); //ตั้งตัวแปรส่งค่าไปยังหน้าเกม รับค่าและส่งค่า
                startActivity(g);
            }
        });


        start_yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent i = getActivity().getIntent();
                UserModel user_head = (UserModel) i.getSerializableExtra("user_head");
                Intent g = new Intent(getActivity(), DataYoga.class);
                g.putExtra("user_head", user_head); //ตั้งตัวแปรส่งค่าไปยังหน้าเกม รับค่าและส่งค่า
                startActivity(g);
            }
        });


    }



}
