package com.example.MoveOn.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataFoods implements Serializable {
    @SerializedName("datafood_id")
    private String datafood_id;

    @SerializedName("datetime_fd")
    private String datetime_fd;

    @SerializedName("categoryfood_id")
    private String categoryfood_id;

    @SerializedName("mealtime_id")
    private String mealtime_id;

    @SerializedName("toppic_fd")
    private String toppic_fd;

    @SerializedName("description_fd")
    private String description_fd;

    @SerializedName("picture_fd")
    private String picture_fd;

    @SerializedName("catdrinks_id")
    private String catdrinks_id;

    @SerializedName("toppic_dk")
    private String toppic_dk;

    @SerializedName("description_dk")
    private String description_dk;

    @SerializedName("picture_dk")
    private String picture_dk;

    @SerializedName("Allpicture")
    private String Allpicture;

    @SerializedName("user_id")
    private int user_id;


    @SerializedName("name_food")
    private String name_food;
    @SerializedName("mealtime")
    private String mealtime;
    @SerializedName("name_drk")
    private String name_drk;


    public String getDatafood_id() { return datafood_id; }

    public void setDatafood_id(String datafood_id) { this.datafood_id = datafood_id; }

    public String getDatetime_fd() { return datetime_fd; }

    public void setDatetime_fd(String datetime_fd) { this.datetime_fd = datetime_fd; }

    public String getCategoryfood_id() { return categoryfood_id; }

    public void setCategoryfood_id(String categoryfood_id) { this.categoryfood_id = categoryfood_id; }

    public String getMealtime_id() { return mealtime_id; }

    public void setMealtime_id(String mealtime_id) { this.mealtime_id = mealtime_id; }

    public String getToppic_fd() { return toppic_fd; }

    public void setToppic_fd(String toppic_fd) { this.toppic_fd = toppic_fd; }

    public String getDescription_fd() { return description_fd; }

    public void setDescription_fd(String description_fd) { this.description_fd = description_fd; }

    public String getPicture_fd() { return picture_fd; }

    public void setPicture_fd(String picture_fd) { this.picture_fd = picture_fd; }

    public String getCatdrinks_id() { return catdrinks_id; }

    public void setCatdrinks_id(String catdrinks_id) { this.catdrinks_id = catdrinks_id; }

    public String getToppic_dk() { return toppic_dk; }

    public void setToppic_dk(String toppic_dk) { this.toppic_dk = toppic_dk; }

    public String getDescription_dk() { return description_dk; }

    public void setDescription_dk(String description_dk) { this.description_dk = description_dk; }

    public String getPicture_dk() { return picture_dk; }

    public void setPicture_dk(String picture_dk) { this.picture_dk = picture_dk; }

    public String getAllpicture() {
        return Allpicture;
    }

    public void setAllpicture(String allpicture) {
        Allpicture = allpicture;
    }

    public int getUser_id() { return user_id; }

    public void setUser_id(int user_id) { this.user_id = user_id; }

    public String getName_food() { return name_food;
    }

    public void setName_food(String name_food) { this.name_food = name_food; }

    public String getMealtime() { return mealtime; }

    public void setMealtime(String mealtime) { this.mealtime = mealtime; }

    public String getName_drk() { return name_drk; }

    public void setName_drk(String name_drk) { this.name_drk = name_drk; }
}
