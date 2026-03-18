package com.example.MoveOn.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.MoveOn.ui.fragment_home;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class MoveOnDB extends SQLiteAssetHelper {

   //ประกาศตัวแปร ฐานข้อมูล
    private static final String DB_NAME = "MoveOn.db"; //ชื่อฐานข้อมูล
    private static final int DB_VERSION = 1; //เวอร์ชั่นฐานข้อมูล



    //คอนสตักเตอร์ไปให้ super Class
    public MoveOnDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    //get ข้อมูลสำหรับติดต่อกับ Ui สำหรับ getSettingMode = ตั้งค่าโหมด, นาฬิกาปลุก
    public int getSettingMode() {
        SQLiteDatabase db = getReadableDatabase(); //สำหรับอ่านข้อมูล
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder(); //เรียกข้อมูล
        String[] sqlSelect = {"Mode"}; //คอลัมน์ Mode
        String sqlTable = "Setting"; //ตาราง Setting

        qb.setTables(sqlTable); //เซ็ตตาราง
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);//เก็บผลลัพธ์มาแสดงโดยใช้ คลาส Cursor
        c.moveToFirst(); //ถ้า c เลื่อนไปยังลำดับแถวตามชื่อเเมธอด
        return c.getInt(c.getColumnIndex("Mode")); //นับจำนวนแถวทและคอลัมน์ทั้งหมดใน Mode ตามลำดับ และคืนค่า
    }

    public void saveSettingMode(int value) {
        SQLiteDatabase db = getReadableDatabase(); //สำหรับอ่านข้อมูล
        String query = "UPDATE Setting SET Mode = " + value; //สำหรับอัพเดท
        db.execSQL(query);
    }

    //get ข้อมูลสำหรับติดต่อกับ Ui สำหรับ getWorkoutDays
    public List<String> getWorkoutDays()
    {
        SQLiteDatabase db = getReadableDatabase(); //สำหรับอ่านข้อมูล
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder(); //เรียกข้อมูล
        String[] sqlSelect = {"Day"}; //คอลัมน์ Mode
        String sqlTable = "WorkoutDays"; //ตาราง WorkoutDays

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null); //เก็บผลลัพธ์มาแสดงโดยใช้ คลาส Cursor

        List<String> result = new ArrayList<>(); //เก็บค่า result เป็น ArrayList
        if (c.moveToFirst()) //ถ้า c เลื่อนไปยังลำดับแถวตามชื่อเเมธอด
        {
            do {
                result.add(c.getString(c.getColumnIndex("Day"))); //ให้ c.getString อ่านข้อมูลปัจจุบัน และให้ c.getColumnIndex นับจำนวนแถวทและคอลัมน์ทั้งหมดใน c ตามลำดับ
            } while (c.moveToNext()); //และให้เลื่อนต่อไป
        }
        return result; //คืนค่าให้กลับ result
    }

    //สำหรับ saveDay บันทึกกิจกรรมในแต่ละวัน
    public void saveDay(String value)
    {
        SQLiteDatabase db = getReadableDatabase();//สำหรับอ่านข้อมูล
        String query = String.format("INSERT INTO WorkoutDays(Day) VALUES('%s');", value); //เพิ่มข้อมูลวัน
        db.execSQL(query);
    }


}
