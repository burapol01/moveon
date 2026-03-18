package com.example.MoveOn.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SettingModel implements Serializable {
    @SerializedName("user_id")
    private int userId;

    @SerializedName("mode")
    private int mode;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
