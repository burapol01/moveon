package com.example.MoveOn.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BmiModel implements Serializable {


    @SerializedName("bmi_id")
    private String bmi_id;

    @SerializedName("bmiresult")
    private float bmiresult;

    @SerializedName("description_bmi")
    private String description_bmi;

    @SerializedName("user_id")
    private int user_id;

    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;

    public String getBmi_id() { return bmi_id; }

    public void setBmi_id(String bmi_id) { this.bmi_id = bmi_id; }

    public float getBmiresult() { return bmiresult; }

    public void setBmiresult(float bmiresult) { this.bmiresult = bmiresult; }

    public String getDescription_bmi() { return description_bmi; }

    public void setDescription_bmi(String description_bmi) { this.description_bmi = description_bmi; }

    public int getUser_id() { return user_id; }

    public void setUser_id(int user_id) { this.user_id = user_id; }

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }

    public String getMassage() { return massage; }

    public void setMassage(String massage) { this.massage = massage; }
}