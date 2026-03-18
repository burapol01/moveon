package com.example.MoveOn.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataNews implements Serializable {

    @SerializedName("datanews_id")
    private String datanews_id;
    @SerializedName("datetime_news")
    private String datetime_news;
    @SerializedName("topic_news")
    private String topic_news;
    @SerializedName("description_news")
    private String description_news;
    @SerializedName("picture_news")
    private String picture_news;

    @SerializedName("user_id")
    private String user_id;

    public String getDatanews_id() { return datanews_id; }

    public void setDatanews_id(String datanews_id) { this.datanews_id = datanews_id; }

    public String getDatetime_news() { return datetime_news; }

    public void setDatetime_news(String datetime_news) { this.datetime_news = datetime_news; }

    public String getTopic_news() { return topic_news; }

    public void setTopic_news(String topic_news) { this.topic_news = topic_news; }

    public String getDescription_news() { return description_news; }

    public void setDescription_news(String description_news) { this.description_news = description_news; }

    public String getPicture_news() { return picture_news; }

    public void setPicture_news(String picture_news) { this.picture_news = picture_news; }

    public String getUser_id() { return user_id; }

    public void setUser_id(String user_id) { this.user_id = user_id; }
}
