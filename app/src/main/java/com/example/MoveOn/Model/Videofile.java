package com.example.MoveOn.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Videofile implements Serializable {

    @SerializedName("video_id")
    private String video_id;
    @SerializedName("datetime_vd")
    private String datetime_vd;
    @SerializedName("category_id")
    private String category_id;
    @SerializedName("name_del")
    private String name_del;
    @SerializedName("name_vd")
    private String name_vd;
    @SerializedName("descriptions_vd")
    private String descriptions_vd;

    @SerializedName("picture_video")
    private String picture_video;

    @SerializedName("video_file")
    private String video_file;



    public String getVideo_id() { return video_id; }

    public void setVideo_id(String video_id) { this.video_id = video_id; }

    public String getDatetime_vd() { return datetime_vd; }

    public void setDatetime_vd(String datetime_vd) { this.datetime_vd = datetime_vd; }

    public String getCategory_id() { return category_id; }

    public void setCategory_id(String category_id) { this.category_id = category_id; }

    public String getName_del() { return name_del; }

    public void setName_del(String name_del) { this.name_del = name_del; }

    public String getName_vd() { return name_vd; }

    public void setName_vd(String name_vd) { this.name_vd = name_vd; }

    public String getDescriptions_vd() { return descriptions_vd; }

    public void setDescriptions_vd(String descriptions_vd) { this.descriptions_vd = descriptions_vd; }

    public String getVideo_file() { return video_file; }

    public void setVideo_file(String video_file) { this.video_file = video_file; }

    public String getPicture_video() { return picture_video; }

    public void setPicture_video(String picture_video) { this.picture_video = picture_video; }
}
