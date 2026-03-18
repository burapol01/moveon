package com.example.MoveOn.Model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserModel implements Serializable {
    @SerializedName("user_id")
    private int user_id;

    @SerializedName("email")
    private String email;

    @SerializedName("user_username")
    private String user_username;

    @SerializedName("user_password")
    private String user_password;

    @SerializedName("picture")
    private String picture;

    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;


    public int getUser_id() { return user_id; }

    public void setUser_id(int user_id) { this.user_id = user_id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getPicture() { return picture; }

    public void setPicture(String picture) { this.picture = picture; }

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }

    public String getMassage() { return massage; }

    public void setMassage(String massage) { this.massage = massage; }
}



