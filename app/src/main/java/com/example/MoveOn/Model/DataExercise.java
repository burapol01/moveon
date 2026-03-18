package com.example.MoveOn.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataExercise implements Serializable {

    @SerializedName("dataex_id")
    private String dataex_id;

    @SerializedName("datetime_ex")
    private String datetime_ex;

    @SerializedName("category_id")
    private String category_id;

    @SerializedName("name_ex")
    private String name_ex;

    @SerializedName("description_ex")
    private String description_ex;

    @SerializedName("picture_ex")
    private String picture_ex;

    @SerializedName("user_id")
    private int user_id;

    @SerializedName("name_del")
    private String name_del;



    public String getDataex_id() { return dataex_id; }

    public void setDataex_id(String dataex_id) { this.dataex_id = dataex_id; }

    public String getDatetime_ex() { return datetime_ex; }

    public void setDatetime_ex(String datetime_ex) { this.datetime_ex = datetime_ex; }

    public String getCategory_id() { return category_id; }

    public void setCategory_id(String category_id) { this.category_id = category_id; }

    public String getName_ex() { return name_ex; }

    public void setName_ex(String name_ex) { this.name_ex = name_ex; }

    public String getDescription_ex() { return description_ex; }

    public void setDescription_ex(String description_ex) { this.description_ex = description_ex; }

    public String getPicture_ex() { return picture_ex; }

    public void setPicture_ex(String picture_ex) { this.picture_ex = picture_ex; }

    public int getUser_id() { return user_id; }

    public void setUser_id(int user_id) { this.user_id = user_id; }

    public String getName_del() { return name_del; }

    public void setName_del(String name_del) { this.name_del = name_del; }
}
