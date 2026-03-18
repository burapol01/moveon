package com.example.MoveOn.Custom;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.HashSet;

public class WorkoutDoneDecorator implements DayViewDecorator {

    //ประกาศตัวแปร
    HashSet<CalendarDay> list; //ข้อมูลปฏิทิน
    ColorDrawable colorDrawable; //กำหนดสี

    //เมธอดสำหรับกำหนดสีวัน
    public WorkoutDoneDecorator(HashSet<CalendarDay>list)
    {
        this.list = list;
        colorDrawable = new ColorDrawable(Color.parseColor("#e57373"));
    }

    //ส่งค่ากลับไปบนปฏิทิน
    @Override
    public boolean shouldDecorate(CalendarDay day) {
       return list.contains(day);
    }

    //แสดงสีในเมธอด decorate
    @Override
    public void decorate(DayViewFacade view)
    {
            view.setBackgroundDrawable(colorDrawable);
    }
}
