package com.example.MoveOn.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class History implements Serializable {

    @SerializedName("user_username")
    private String user_username;

    @SerializedName("name_del")
    private String name_del;

    @SerializedName("datetime")
    private String datetime;

    @SerializedName("picture")
    private String picture;

    @SerializedName("user_id")
    private int user_id;

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getName_del() {
        return name_del;
    }

    public void setName_del(String name_del) {
        this.name_del = name_del;
    }

    public String getDate_time() {
        return datetime;
    }

    public void setDate_time(String date_time) {
        this.datetime = date_time;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
