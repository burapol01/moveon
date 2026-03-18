package com.example.MoveOn.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WorkoutDay implements Serializable {


    @SerializedName("ID")
    private String ID;
    @SerializedName("Day")
    private String Day;

    @SerializedName("category_id")
    private int category_id;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;


    public String getID() { return ID; }

    public void setID(String ID) { this.ID = ID; }

    public String getDay() { return Day; }

    public void setDay(String day) { Day = day; }

    public int getCategory_id() { return category_id; }

    public void setCategory_id(int category_id) { this.category_id = category_id; }

    public String getUser_id() { return user_id; }

    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }

    public String getMassage() { return massage; }

    public void setMassage(String massage) { this.massage = massage; }
}
