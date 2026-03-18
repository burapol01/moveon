package com.example.MoveOn.Model;

import com.google.gson.annotations.SerializedName;

public class ApiResponse<T> {
    @SerializedName("value")
    private String value;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private T data;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public String getMassage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return "1".equals(value);
    }
}
