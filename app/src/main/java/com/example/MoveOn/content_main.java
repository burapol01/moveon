package com.example.MoveOn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.RecoverySystem;


import com.example.MoveOn.Adapter.CustomAdapter;
import com.example.MoveOn.item.ChildItem;
import com.example.MoveOn.item.SectionItem;

import java.util.ArrayList;

public class content_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_main);

        ArrayList item = new ArrayList();
        item.add(new SectionItem("ข้อมูลผู้ใช้"));
        item.add(new ChildItem("ตั้งค่าผู้ใช้"));
        item.add(new ChildItem("เปลี่ยนรหัสผ่าน"));
        item.add(new ChildItem("ติดต่อประชาสัมพันธ์"));
        item.add(new ChildItem("ออกจากระบบ"));


        item.add(new SectionItem("ข้อมูลผู้ใช้"));
        item.add(new ChildItem("ตั้งค่าผู้ใช้"));
        item.add(new ChildItem("เปลี่ยนรหัสผ่าน"));
        item.add(new ChildItem("ติดต่อประชาสัมพันธ์"));
        item.add(new ChildItem("ออกจากระบบ"));





        CustomAdapter adapter = new CustomAdapter(this, item);
        RecyclerView rcv =(RecyclerView)findViewById(R.id.rvc_test);
        rcv.setAdapter(adapter);
        rcv.setLayoutManager(new LinearLayoutManager(this));

    }
}
