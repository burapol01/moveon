package com.example.MoveOn.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Datauser implements Serializable {

    @SerializedName("user_id")
    private int user_id;

    @SerializedName("userfullname")
    private String userfullname;

    @SerializedName("email")
    private String email;

    @SerializedName("picture")
    private String picture;

    public int getUser_id() { return user_id; }

    public void setUser_id(int user_id) { this.user_id = user_id; }

    public String getUserfullname() { return userfullname; }

    public void setUserfullname(String userfullname) { this.userfullname = userfullname; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPicture() { return picture; }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
